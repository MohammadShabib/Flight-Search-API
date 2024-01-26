package com.amadeus.flightsearchengine.resttemplatecalls;

import com.amadeus.flightsearchengine.dto.FlightDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Responsible for fetching data from Amadeus APIs
 */
@Service
public class AmadeusApiService
{
    private final RestTemplate restTemplate;
    private final String amadeusClientId;

    private final String amadeusClientSercret;


    public AmadeusApiService(RestTemplate aInRestTemplate,
            @Value("${security.amadeus.clientId}") String aInAmadeusClientId,
            @Value("${security.amadeus.clientSecret}") String aInAmadeusClientSercret)
    {
        restTemplate = aInRestTemplate;

        amadeusClientId = aInAmadeusClientId;
        amadeusClientSercret = aInAmadeusClientSercret;
    }

    /**
     * Retrieves flight offers from Amadeus API endpoint.
     *
     * @param aInFlightDto  requested flight
     * @param aInMaxFlights maximum number of flight
     * @return List of {@link FlightDto}
     */
    public List<FlightDto> getFlightOffers(FlightDto aInFlightDto, int aInMaxFlights)
    {
        try
        {
            String lDepartedDate =
                    aInFlightDto.getDepartureDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE);
            UriComponentsBuilder builder =
                    UriComponentsBuilder.fromUriString(
                                    "https://test.api.amadeus.com/v2/shopping/flight-offers")
                            .queryParam("originLocationCode", aInFlightDto.getDepartureAirport())
                            .queryParam("destinationLocationCode", aInFlightDto.getArrivalAirport())
                            .queryParam("departureDate", lDepartedDate).queryParam("adults", 1)
                            .queryParam("nonStop", true)
                            .queryParam("max", aInMaxFlights);

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(getToken());

            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            ResponseEntity<String> response =
                    restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
                            String.class);


            return extractFlightDto(response.getBody());
        }
        catch (Exception aInE)
        {
            return new ArrayList<>();
        }
    }

    /**
     * Get Bearer token for Amadeus authentication
     *
     * @return Bearer token
     * @throws JsonProcessingException
     */
    private String getToken() throws JsonProcessingException
    {
        String lOutAccessToken = null;
        MultiValueMap<String, String> lMultiValueMap = new LinkedMultiValueMap<>();
        lMultiValueMap.add("grant_type", "client_credentials");
        lMultiValueMap.add("client_id", amadeusClientId);
        lMultiValueMap.add("client_secret", amadeusClientSercret);

        HttpEntity<MultiValueMap<String, String>> lAuthRequest =
                new HttpEntity<>(lMultiValueMap, new HttpHeaders());

        ResponseEntity<String> lAuthResponse = restTemplate.postForEntity(
                "https://test.api.amadeus.com/v1/security/oauth2/token", lAuthRequest,
                String.class);
        if (lAuthResponse.getStatusCode() == HttpStatus.OK)
        {
            String lAuthResponseBody = lAuthResponse.getBody();
            ObjectMapper lObjectMapper = new ObjectMapper();


            JsonNode lJsonNode = lObjectMapper.readTree(lAuthResponseBody);
            lOutAccessToken = lJsonNode.path("access_token").asText();
        }

        return lOutAccessToken;
    }

    /**
     * Extracts a list of flight information from a JSON response.
     * This method parses the JSON response to extract essential flight details  and convert them to {@link FlightDto}
     *
     * @param aInJson The JSON response string from the flight offers API.
     * @return List of {@link FlightDto}
     */
    private static List<FlightDto> extractFlightDto(String aInJson) throws IOException
    {
        ObjectMapper lObjectMapper = new ObjectMapper();
        JsonNode lDataList = lObjectMapper.readTree(aInJson).path("data");

        List<FlightDto> lOutFlightDtoList = new ArrayList<>();

        for (JsonNode lData : lDataList)
        {
            FlightDto lFlightDto = new FlightDto();

            lFlightDto.setId(lData.path("id").asText());

            JsonNode lSegmentNode = lData.path("itineraries").get(0).path("segments").get(0);
            lFlightDto.setDepartureAirport(
                    lSegmentNode.path("departure").path("iataCode").asText());
            lFlightDto.setArrivalAirport(lSegmentNode.path("arrival").path("iataCode").asText());

            DateTimeFormatter lDateTimeFormatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            lFlightDto.setDepartureDateTime(
                    LocalDateTime.parse(lSegmentNode.path("departure").path("at").asText(),
                            lDateTimeFormatter));

            lFlightDto.setPrice(lData.path("price").path("total").asDouble());

            lOutFlightDtoList.add(lFlightDto);
        }

        return lOutFlightDtoList;
    }
}


