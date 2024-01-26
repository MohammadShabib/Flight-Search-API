package com.amadeus.flightsearchengine.service;

import com.amadeus.flightsearchengine.dto.FlightDto;
import com.amadeus.flightsearchengine.dto.TwoWayFlightDto;
import com.amadeus.flightsearchengine.model.FlightModel;

import java.util.List;

public interface FightSearchService
{
    List<FlightDto> searchFlightsOneWay(FlightModel aInFlightModel, boolean aInThirdPartySearch);

    TwoWayFlightDto searchFlightsTwoWay(FlightModel aInFlightModel);

    List<FlightModel> addFlights(List<FlightModel> aInFlightModelList);

    FlightModel updateFlight(FlightModel aInFlightModel);

    List<FlightModel> getAllFlights();

    FlightModel getFlightById(String aInFlightId);

    boolean deleteFlightById(String aInFlightId);
}
