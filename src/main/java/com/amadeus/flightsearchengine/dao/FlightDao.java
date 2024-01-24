package com.amadeus.flightsearchengine.dao;

import com.amadeus.flightsearchengine.model.FlightModel;

import java.util.List;

public interface FlightDao
{
    FlightModel create(FlightModel aInData);

    List<FlightModel> createBulk(List<FlightModel> aInData);

    FlightModel update(FlightModel aInData);

    FlightModel readById(String aInName);

    boolean delete(String aInName);


    List<FlightModel> find(FlightModel aInFlightModel);
}
