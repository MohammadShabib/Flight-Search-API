package com.amadeus.flightsearchengine.controller;


import com.amadeus.flightsearchengine.dao.AirportDao;
import com.amadeus.flightsearchengine.model.AirportModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class FlightController
{
    private final AirportDao airportDao;

    public FlightController(AirportDao aInAirportDao)
    {
        airportDao = aInAirportDao;
    }


    @GetMapping("/test")
    public ResponseEntity<String> test()
    {
        AirportModel lAirportPar = new AirportModel();
        lAirportPar.setId("23");
        lAirportPar.setCity("Ankara23");
        airportDao.create(lAirportPar) ;
        airportDao.readById("23") ;
        return ResponseEntity.ok("File exists.");
    }
}
