package com.amadeus.flightsearchengine.dao.impl;

import com.amadeus.flightsearchengine.aerospike.EntityDAO;
import com.amadeus.flightsearchengine.dao.FlightDao;
import com.amadeus.flightsearchengine.dto.FlightDto;
import com.amadeus.flightsearchengine.model.FlightModel;
import com.amadeus.flightsearchengine.par.FlightPar;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Function;

@Repository
public class FlightDaoImpl extends EntityDAO<FlightModel, FlightPar, String> implements FlightDao
{
    @Override
    protected FlightPar convertToPar(FlightModel aInModel)
    {
        FlightPar lOutPar = new FlightPar();
        lOutPar.setId(aInModel.getId());
        lOutPar.setDepartureAirport(aInModel.getDepartureAirport());
        lOutPar.setArrivalAirport(aInModel.getArrivalAirport());
        lOutPar.setDepartureDateTime(aInModel.getDepartureDateTime());
        lOutPar.setReturnDateTime(aInModel.getReturnDateTime());
        lOutPar.setPrice(aInModel.getPrice());
        return lOutPar;
    }

    @Override
    protected FlightModel convertToModel(FlightPar aInPar)
    {
        FlightModel lOutModel = new FlightModel();
        lOutModel.setId(aInPar.getId());
        lOutModel.setDepartureAirport(aInPar.getDepartureAirport());
        lOutModel.setArrivalAirport(aInPar.getArrivalAirport());
        lOutModel.setDepartureDateTime(aInPar.getDepartureDateTime());
        lOutModel.setReturnDateTime(aInPar.getReturnDateTime());
        lOutModel.setPrice(aInPar.getPrice());

        return lOutModel;
    }

    @Override
    protected Class<FlightPar> getParClass()
    {
        return FlightPar.class;
    }

    @Override
    public List<FlightModel> find(FlightDto aInFlightDto)
    {
        Function<FlightPar, Boolean> lFunction = flight -> {
            FlightModel lFlightModel = convertToModel(flight);
            if (isDepartureFlightSuitable(aInFlightDto, lFlightModel) ||
                    isReturnFlightSuitable(aInFlightDto, lFlightModel))
            {
                return true;
            }
            return false;
        };
        return find(lFunction);
    }

    /**
     * Determines if a given <b>departure</b> flight is suitable compared to the wantedFlight
     *
     * @param aInWantedFlight    wanted flight
     * @param aInFlightToCompare flight to compare
     * @return true if compared flight is suitable, false if not
     */
    private static boolean isDepartureFlightSuitable(FlightDto aInWantedFlight, FlightModel aInFlightToCompare)
    {
        LocalDateTime lWantedDay = aInWantedFlight.getDepartureDateTime();
        LocalDateTime lNextDay = lWantedDay.truncatedTo(ChronoUnit.DAYS).plusDays(1);
        if (!aInFlightToCompare.getDepartureAirport().equals(aInWantedFlight.getDepartureAirport()))
        {
            return false;
        }
        if (!aInFlightToCompare.getArrivalAirport().equals(aInWantedFlight.getArrivalAirport()))
        {
            return false;
        }
        if (aInFlightToCompare.getDepartureDateTime().isBefore(lWantedDay))
        {
            return false;
        }
        if (aInFlightToCompare.getDepartureDateTime().isAfter(lNextDay))
        {
            return false;
        }
        return true;
    }

    /**
     * Determines if a given <b>return</b> flight is suitable compared to the wantedFlight
     *
     * @param aInWantedFlight    wanted flight
     * @param aInFlightToCompare flight to compare
     * @return true if compared flight is suitable, false if not
     */
    private static boolean isReturnFlightSuitable(FlightDto aInWantedFlight, FlightModel aInFlightToCompare)
    {
        LocalDateTime lWantedDay = aInWantedFlight.getDepartureDateTime();
        LocalDateTime lNextDay = lWantedDay.truncatedTo(ChronoUnit.DAYS).plusDays(1);
        if (!aInFlightToCompare.getArrivalAirport().equals(aInWantedFlight.getDepartureAirport()))
        {
            return false;
        }
        if (!aInFlightToCompare.getDepartureAirport().equals(aInWantedFlight.getArrivalAirport()))
        {
            return false;
        }
        if (aInFlightToCompare.getReturnDateTime().isBefore(lWantedDay))
        {
            return false;
        }
        if (aInFlightToCompare.getReturnDateTime().isAfter(lNextDay))
        {
            return false;
        }
        return true;
    }


}
