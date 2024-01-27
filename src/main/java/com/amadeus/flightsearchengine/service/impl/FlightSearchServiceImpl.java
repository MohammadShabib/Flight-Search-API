package com.amadeus.flightsearchengine.service.impl;

import com.amadeus.flightsearchengine.dao.FlightDao;
import com.amadeus.flightsearchengine.dto.FlightDto;
import com.amadeus.flightsearchengine.dto.TwoWayFlightDto;
import com.amadeus.flightsearchengine.model.FlightModel;
import com.amadeus.flightsearchengine.resttemplatecalls.AmadeusApiService;
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
    private final AmadeusApiService amadeusApiService;

    public FlightSearchServiceImpl(FlightDao aInFlightDao, AmadeusApiService aInAmadeusApiService)
    {
        flightDao = aInFlightDao;
        amadeusApiService = aInAmadeusApiService;
    }

    @Override
    public List<FlightDto> searchFlightsOneWay(FlightModel aInFlightModel, boolean aInThirdPartySearch)
    {
        List<FlightDto> lOutFlightDto = null;
        FlightDto lRequestedFlightDto = convertModelToDTO(aInFlightModel, true);

        if (aInThirdPartySearch)
        {
            lOutFlightDto = amadeusApiService.getFlightOffers(lRequestedFlightDto, 5);
            List<FlightModel> lFlightModelList =
                    lOutFlightDto.stream().map(FlightDto::convertDtoToModel).toList();
            flightDao.createBulk(lFlightModelList);
        }
        else
        {
            List<FlightModel> lDepartureFlights = flightDao.find(lRequestedFlightDto);
            lOutFlightDto = new ArrayList<>(convertBulkModelToDTO(lRequestedFlightDto, lDepartureFlights));
        }

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

    @Override
    public FlightModel updateFlight(FlightModel aInFlightModel)
    {
        return flightDao.update(aInFlightModel);
    }

    @Override
    public List<FlightModel> getAllFlights()
    {
        return flightDao.readAll();
    }

    @Override
    public FlightModel getFlightById(String aInFlightId)
    {
        return flightDao.readById(aInFlightId);
    }

    @Override
    public boolean deleteFlightById(String aInFlightId)
    {
        return flightDao.delete(aInFlightId);
    }
}
