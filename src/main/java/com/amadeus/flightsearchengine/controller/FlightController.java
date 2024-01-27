package com.amadeus.flightsearchengine.controller;


import com.amadeus.flightsearchengine.dto.FlightDto;
import com.amadeus.flightsearchengine.dto.TwoWayFlightDto;
import com.amadeus.flightsearchengine.model.FlightModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
@Tag(name = "Flights", description = "Operations related to flights")
@RequestMapping(value = "flight", produces = MediaType.APPLICATION_JSON_VALUE)
@SecurityRequirement(name = "bearerAuth")
public interface FlightController
{
    //https://www.skyscanner.com.tr/transport/flights/esb/ista/240206/


    @GetMapping("/{departureAirport}/{arrivalAirport}/{departureDateTime}")
    @Operation(summary = "Get one-way flights", description = "Retrieve a list of one-way flights based on departure and arrival airports and departure date", responses = {
            @ApiResponse(description = "List of flights", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = FlightDto.class)))})
    ResponseEntity<List<FlightDto>> getOneWayFlight(
            @Parameter(example = "ESB") @PathVariable String departureAirport,
            @Parameter(example = "SAW") @PathVariable String arrivalAirport,
            @Parameter(description = "Departure date in yyMMdd format", example = "240215") @PathVariable @DateTimeFormat(pattern = "yyMMdd") LocalDate departureDateTime,
            @Parameter(description = "Boolean flag to enable third party search. If true, third party search will be used, otherwise database search. Defaults to false.", required = false) @RequestParam(name = "thirdPartySearch", defaultValue = "false") boolean aInThirdPartySearch);


    @GetMapping("/{departureAirport}/{arrivalAirport}/{departureDateTime}/{returnDateTime}")

    @Operation(summary = "Get two-way flights", description = "Retrieve details of two-way flights based on departure and arrival airports, and departure and return dates", responses = {
            @ApiResponse(description = "Two-way flight details", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TwoWayFlightDto.class)))})
    ResponseEntity<TwoWayFlightDto> getTwoWayFlight(
            @Parameter(example = "ESB") @PathVariable String departureAirport,
            @Parameter(example = "SAW") @PathVariable String arrivalAirport,
            @Parameter(description = "Departure date in yyMMdd format", example = "240215") @PathVariable @DateTimeFormat(pattern = "yyMMdd") LocalDate departureDateTime,
            @Parameter(description = "Departure date in yyMMdd format", example = "240216") @PathVariable @DateTimeFormat(pattern = "yyMMdd") LocalDate returnDateTime);


    @Operation(summary = "Add Flights", description = "Add a list of new flights",

            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = FlightModel.class), examples = @ExampleObject(value =
                    "[\n" + "    {\n" + "        \"id\": \"1\",\n" +
                            "        \"departureAirport\": \"SAW\",\n" +
                            "        \"arrivalAirport\": \"IST\",\n" +
                            "        \"departureDateTime\": \"2024-02-15T15:30\",\n" +
                            "        \"returnDateTime\": \"2024-02-16T15:30\",\n" +
                            "        \"price\": \"30\"\n" +
                            "    }\n" + "]"))), responses = {
            @ApiResponse(description = "List of added flights", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FlightModel.class)))})
    @PostMapping("")
    ResponseEntity<List<FlightModel>> addFlights(
            @Parameter(description = "List of flights to be added", required = true) @Valid @RequestBody List<FlightModel> aInRequest);

    @PutMapping("")
    @Operation(summary = "Update a flight", description = "Update the details of a flight based on the provided FlightModel", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = FlightModel.class), examples = @ExampleObject(value =
            "{\n" + "    \"id\": \"1\",\n" +
                    "    \"departureAirport\": \"ESB\",\n" +
                    "    \"arrivalAirport\": \"SAW\",\n" +
                    "    \"departureDateTime\": \"2024-02-15T15:30:30\",\n" +
                    "    \"returnDateTime\": \"2024-02-16T15:30\",\n" +
                    "    \"price\": \"50\"\n" +
                    "}"))))
    ResponseEntity<FlightModel> updateFlight(
            @Parameter(description = "Flight to be updated", required = true) @Valid @RequestBody FlightModel aInRequest);

    @GetMapping("/all")
    @Operation(summary = "Get all flights", description = "Retrieve a list of all flights available")
    ResponseEntity<List<FlightModel>> getAllFlights();

    @GetMapping("")
    @Operation(summary = "Get a flight by ID", description = "Retrieve details of a flight based on the provided flight ID")
    ResponseEntity<FlightModel> getFlightById( @Parameter(description = "Flight Id", example = "1") @RequestParam(name = "id") String aInFlightId);

    @DeleteMapping("")
    @Operation(summary = "Delete a flight by ID", description = "Delete a flight based on the provided flight ID")
    ResponseEntity<Boolean> deleteFlightById( @Parameter(description = "Flight Id", example = "1") @RequestParam(name = "id") String aInFlightId);
}