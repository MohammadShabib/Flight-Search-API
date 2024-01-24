package com.amadeus.flightsearchengine.controller;


import com.amadeus.flightsearchengine.dao.AirportDao;
import com.amadeus.flightsearchengine.dao.FlightDao;
import com.amadeus.flightsearchengine.model.AirportModel;
import com.amadeus.flightsearchengine.model.FlightModel;
import com.amadeus.flightsearchengine.service.FightSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class FlightController
{
    private final AirportDao airportDao;

    private final FlightDao flightDao;

    private final FightSearchService fightSearchService;

    public FlightController(AirportDao aInAirportDao, FlightDao aInFlightDao, FightSearchService aInFightSearchService)
    {
        airportDao = aInAirportDao;
        flightDao = aInFlightDao;
        fightSearchService = aInFightSearchService;
    }


    //https://www.skyscanner.com.tr/transport/flights/esb/ista/240127/240206/
    @GetMapping("/flights/{departureAirport}/{arrivalAirport}/{departureDateTime}")
    public ResponseEntity<List<FlightModel>> getOneWayFlight(
            FlightModel aInFlightModel)
    {
        return  ResponseEntity.ok(fightSearchService.searchFlights(aInFlightModel, false));
    }

    @GetMapping("/flights/{departureAirport}/{arrivalAirport}/{departureDateTime}/{returnDateTime}")
    public Map<String, Object> getTwoWayFlight(
            FlightModel aInFlightModel)
    {
        Map<String, Object> response = new HashMap<>();

        return response;
    }

    @PostMapping("/flight/add")
    public ResponseEntity<String> test(@Valid @RequestBody List<FlightModel> aInRequest)
    {
        String lAirportId = "06";
        AirportModel lAirportModel = new AirportModel(lAirportId, "Ankara");
        airportDao.create(lAirportModel);
        airportDao.readById(lAirportId);
        lAirportModel.setCity("Istanbul");
        airportDao.update(lAirportModel);
        airportDao.delete(lAirportId);

        flightDao.createBulk(aInRequest);

        return ResponseEntity.ok("File exists.");
    }
}
