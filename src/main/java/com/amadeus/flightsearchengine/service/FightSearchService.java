package com.amadeus.flightsearchengine.service;

import com.amadeus.flightsearchengine.model.FlightModel;

import java.util.List;

public interface FightSearchService
{

    public List<FlightModel> searchFlights(FlightModel aInFlightModel,boolean aInIsTwoWay);
}
