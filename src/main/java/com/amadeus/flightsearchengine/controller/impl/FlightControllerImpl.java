package com.amadeus.flightsearchengine.controller.impl;


import com.amadeus.flightsearchengine.controller.FlightController;
import com.amadeus.flightsearchengine.dto.FlightDto;
import com.amadeus.flightsearchengine.dto.TwoWayFlightDto;
import com.amadeus.flightsearchengine.model.FlightModel;
import com.amadeus.flightsearchengine.service.FightSearchService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
public class FlightControllerImpl implements FlightController
{
    private final FightSearchService fightSearchService;

    public FlightControllerImpl(FightSearchService aInFightSearchService)
    {
        fightSearchService = aInFightSearchService;
    }

    @Override
    public ResponseEntity<List<FlightDto>> getOneWayFlight(String departureAirport,
            String arrivalAirport,
            LocalDate departureDateTime, boolean aInThirdPartySearch)
    {
        FlightModel lFlightModel =
                new FlightModel(null, departureAirport, arrivalAirport,
                        departureDateTime.atStartOfDay(), null, null);
        return ResponseEntity.ok(
                fightSearchService.searchFlightsOneWay(lFlightModel, aInThirdPartySearch));
    }

    @Override
    public ResponseEntity<TwoWayFlightDto> getTwoWayFlight(String departureAirport,
            String arrivalAirport,
            LocalDate departureDateTime, LocalDate returnDateTime)
    {
        FlightModel lFlightModel =
                new FlightModel(null, departureAirport, arrivalAirport,
                        departureDateTime.atStartOfDay(),
                        returnDateTime.atStartOfDay(), null);
        return ResponseEntity.ok(fightSearchService.searchFlightsTwoWay(lFlightModel));
    }

    @Override
    public ResponseEntity<List<FlightModel>> addFlights(
            @Parameter(description = "List of flights to be added") @Valid @RequestBody List<FlightModel> aInRequest)
    {
        return ResponseEntity.ok(fightSearchService.addFlights(aInRequest));
    }

    @Override
    public ResponseEntity<FlightModel> updateFlight(FlightModel aInRequest)
    {
        return ResponseEntity.ok(fightSearchService.updateFlight(aInRequest));
    }

    @Override
    public ResponseEntity<List<FlightModel>> getAllFlights()
    {
        return ResponseEntity.ok(fightSearchService.getAllFlights());
    }

    @Override
    public ResponseEntity<FlightModel> getFlightById(String aInFlightId)
    {
        return ResponseEntity.ok(fightSearchService.getFlightById(aInFlightId));
    }

    @Override
    public ResponseEntity<Boolean> deleteFlightById(String aInFlightId)
    {
        return ResponseEntity.ok(fightSearchService.deleteFlightById(aInFlightId));
    }
}
