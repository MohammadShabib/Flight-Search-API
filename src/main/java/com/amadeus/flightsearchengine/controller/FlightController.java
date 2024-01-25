package com.amadeus.flightsearchengine.controller;


import com.amadeus.flightsearchengine.dto.FlightDto;
import com.amadeus.flightsearchengine.dto.TwoWayFlightDto;
import com.amadeus.flightsearchengine.model.FlightModel;
import com.amadeus.flightsearchengine.service.FightSearchService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
public class FlightController
{
    private final FightSearchService fightSearchService;

    public FlightController(FightSearchService aInFightSearchService)
    {
        fightSearchService = aInFightSearchService;
    }

    //https://www.skyscanner.com.tr/transport/flights/esb/ista/240206/
    @GetMapping("/flight/{departureAirport}/{arrivalAirport}/{departureDateTime}")
    public ResponseEntity<List<FlightDto>> getOneWayFlight(@PathVariable String departureAirport,
            @PathVariable String arrivalAirport,
            @PathVariable @DateTimeFormat(pattern = "yyMMdd") LocalDate departureDateTime)
    {
        FlightModel lFlightModel =
                new FlightModel(null, departureAirport, arrivalAirport, departureDateTime.atStartOfDay(), null, null);
        return ResponseEntity.ok(fightSearchService.searchFlightsOneWay(lFlightModel));
    }

    @GetMapping("/flight/{departureAirport}/{arrivalAirport}/{departureDateTime}/{returnDateTime}")
    public ResponseEntity<TwoWayFlightDto> getTwoWayFlight(@PathVariable String departureAirport,
            @PathVariable String arrivalAirport,
            @PathVariable @DateTimeFormat(pattern = "yyMMdd") LocalDate departureDateTime,
            @PathVariable @DateTimeFormat(pattern = "yyMMdd") LocalDate returnDateTime)
    {

        FlightModel lFlightModel =
                new FlightModel(null, departureAirport, arrivalAirport, departureDateTime.atStartOfDay(),
                        returnDateTime.atStartOfDay(), null);
        return ResponseEntity.ok(fightSearchService.searchFlightsTwoWay(lFlightModel));
    }

    @PostMapping("/flight/add")
    public ResponseEntity<List<FlightModel>> addFlights(@Valid @RequestBody List<FlightModel> aInRequest)
    {
        return ResponseEntity.ok(fightSearchService.addFlights(aInRequest));
    }
}
