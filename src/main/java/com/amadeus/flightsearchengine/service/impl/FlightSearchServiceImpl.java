package com.amadeus.flightsearchengine.service.impl;

import com.amadeus.flightsearchengine.dao.FlightDao;
import com.amadeus.flightsearchengine.dto.FlightDto;
import com.amadeus.flightsearchengine.dto.TwoWayFlightDto;
import com.amadeus.flightsearchengine.model.FlightModel;
import com.amadeus.flightsearchengine.service.FightSearchService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.amadeus.flightsearchengine.dto.FlightDto.convertBulkModelToDTO;
import static com.amadeus.flightsearchengine.dto.FlightDto.convertModelToDTO;

@Service
public class FlightSearchServiceImpl implements FightSearchService
{
    private final FlightDao flightDao;

    public FlightSearchServiceImpl(FlightDao aInFlightDao)
    {
        flightDao = aInFlightDao;
    }

    @Override
    public List<FlightDto> searchFlightsOneWay(FlightModel aInFlightModel)
    {
        FlightDto lFlightDto = convertModelToDTO(aInFlightModel, true);
        List<FlightModel> lDepartureFlights =  flightDao.find(lFlightDto);
        List<FlightDto> lOutFlightDto = new ArrayList<>(convertBulkModelToDTO(lFlightDto, lDepartureFlights));

        return lOutFlightDto;
    }

    @Override
    public TwoWayFlightDto searchFlightsTwoWay(FlightModel aInFlightModel)
    {
        TwoWayFlightDto lTwoWayFlightDto = new TwoWayFlightDto();

        FlightDto lWantedDepartureFlight = convertModelToDTO(aInFlightModel, true);
        List<FlightModel> lDepartureFlights = flightDao.find(lWantedDepartureFlight);
        lTwoWayFlightDto.setDepartureFlight(convertBulkModelToDTO(lWantedDepartureFlight, lDepartureFlights));

        FlightDto lWantedReturnFlight = convertModelToDTO(aInFlightModel, false);
        List<FlightModel> lReturnFlights = flightDao.find(lWantedReturnFlight);
        lTwoWayFlightDto.setReturnFlight(convertBulkModelToDTO(lWantedReturnFlight, lReturnFlights));

        return lTwoWayFlightDto;
    }

    @Override
    public List<FlightModel> addFlights(List<FlightModel> aInFlightModelList)
    {
        return flightDao.createBulk(aInFlightModelList);
    }
}
