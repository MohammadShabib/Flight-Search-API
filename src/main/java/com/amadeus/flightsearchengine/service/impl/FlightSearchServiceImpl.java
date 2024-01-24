package com.amadeus.flightsearchengine.service.impl;

import com.amadeus.flightsearchengine.dao.FlightDao;
import com.amadeus.flightsearchengine.model.FlightModel;
import com.amadeus.flightsearchengine.service.FightSearchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightSearchServiceImpl implements FightSearchService
{
    private final FlightDao flightDao;

    public FlightSearchServiceImpl(FlightDao aInFlightDao)
    {
        flightDao = aInFlightDao;
    }

    @Override
    public List<FlightModel> searchFlights(FlightModel aInFlightModel, boolean aInIsTwoWay)
    {
        return flightDao.find(aInFlightModel);
    }
}
