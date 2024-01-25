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
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
@Tag(name = "Flights", description = "Operations related to flights")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public interface FlightController
{
    //https://www.skyscanner.com.tr/transport/flights/esb/ista/240206/


    @GetMapping("/flight/{departureAirport}/{arrivalAirport}/{departureDateTime}")
    @Operation(summary = "Get one-way flights", description = "Retrieve a list of one-way flights based on departure and arrival airports and departure date", responses = {
            @ApiResponse(description = "List of flights", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = FlightDto.class)))})
    ResponseEntity<List<FlightDto>> getOneWayFlight(@Parameter(example = "ank") @PathVariable String departureAirport,
            @Parameter(example = "ist") @PathVariable String arrivalAirport,
            @Parameter(description = "Departure date in yyMMdd format", example = "240125") @PathVariable @DateTimeFormat(pattern = "yyMMdd") LocalDate departureDateTime);


    @GetMapping("/flight/{departureAirport}/{arrivalAirport}/{departureDateTime}/{returnDateTime}")

    @Operation(summary = "Get two-way flights", description = "Retrieve details of two-way flights based on departure and arrival airports, and departure and return dates", responses = {
            @ApiResponse(description = "Two-way flight details", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TwoWayFlightDto.class)))})
    ResponseEntity<TwoWayFlightDto> getTwoWayFlight(@Parameter(example = "ank") @PathVariable String departureAirport,
            @Parameter(example = "ist") @PathVariable String arrivalAirport,
            @Parameter(description = "Departure date in yyMMdd format", example = "240125") @PathVariable @DateTimeFormat(pattern = "yyMMdd") LocalDate departureDateTime,
            @Parameter(description = "Departure date in yyMMdd format", example = "240126") @PathVariable @DateTimeFormat(pattern = "yyMMdd") LocalDate returnDateTime);


    @Operation(summary = "Add Flights", description = "Add a list of new flights",

            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = FlightModel.class), examples = @ExampleObject(value =
                    "[\n" + "    {\n" + "        \"id\": \"1\",\n" + "        \"departureAirport\": \"ank\",\n" +
                            "        \"arrivalAirport\": \"ist\",\n" +
                            "        \"departureDateTime\": \"2024-01-25T15:30\",\n" +
                            "        \"returnDateTime\": \"2024-01-26T15:30\",\n" + "        \"price\": \"30\"\n" +
                            "    }\n" + "]"))), responses = {
            @ApiResponse(description = "List of added flights", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FlightModel.class)))})
    @PostMapping("/flight/add")
    ResponseEntity<List<FlightModel>> addFlights(
            @Parameter(description = "List of flights to be added", required = true) @Valid @RequestBody List<FlightModel> aInRequest);


}