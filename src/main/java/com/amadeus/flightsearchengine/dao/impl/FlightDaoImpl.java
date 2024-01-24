package com.amadeus.flightsearchengine.dao.impl;

import com.amadeus.flightsearchengine.aerospike.EntityDAO;
import com.amadeus.flightsearchengine.dao.FlightDao;
import com.amadeus.flightsearchengine.model.FlightModel;
import com.amadeus.flightsearchengine.par.FlightPar;
import org.springframework.stereotype.Repository;

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
}
