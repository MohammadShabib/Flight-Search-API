package com.amadeus.flightsearchengine.controller;


import com.amadeus.flightsearchengine.dao.AirportDao;
import com.amadeus.flightsearchengine.dao.FlightDao;
import com.amadeus.flightsearchengine.model.AirportModel;
import com.amadeus.flightsearchengine.model.FlightModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class FlightController
{
    private final AirportDao airportDao;

    private final FlightDao flightDao;

    public FlightController(AirportDao aInAirportDao, FlightDao aInFlightDao)
    {
        airportDao = aInAirportDao;
        flightDao = aInFlightDao;
    }


    @GetMapping("/test")
    public ResponseEntity<String> test()
    {
        String lAirportId = "06";
        AirportModel lAirportModel = new AirportModel(lAirportId, "Ankara");
        airportDao.create(lAirportModel);
        airportDao.readById(lAirportId);
        lAirportModel.setCity("Istanbul");
        airportDao.update(lAirportModel);
        airportDao.delete(lAirportId);

        String lFlightId = "0634";
        FlightModel lFlightModel =
                new FlightModel(lFlightId, "Ankara", "Istanbul", 1706126946000l, 1706126964000l, 33d);
        flightDao.create(lFlightModel);
        flightDao.readById(lFlightId);
        lFlightModel.setPrice(1000d);
        flightDao.update(lFlightModel);
        flightDao.delete(lFlightId);

        return ResponseEntity.ok("File exists.");
    }
}
