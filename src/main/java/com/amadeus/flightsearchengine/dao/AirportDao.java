package com.amadeus.flightsearchengine.dao;

import com.amadeus.flightsearchengine.model.AirportModel;

public interface AirportDao
{
    AirportModel create(AirportModel aInData);

    AirportModel update(AirportModel aInData);

    AirportModel readById(String aInName);

    boolean delete(String aInName);
}
