package com.amadeus.flightsearchengine.dao;

import com.amadeus.flightsearchengine.model.FlightModel;

public interface FlightDao
{
    FlightModel create(FlightModel aInData);

    FlightModel update(FlightModel aInData);

    FlightModel readById(String aInName);

    boolean delete(String aInName);
}
