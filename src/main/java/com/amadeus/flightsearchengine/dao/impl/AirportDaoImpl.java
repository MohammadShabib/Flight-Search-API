package com.amadeus.flightsearchengine.dao.impl;

import com.amadeus.flightsearchengine.aerospike.EntityDAO;
import com.amadeus.flightsearchengine.dao.AirportDao;
import com.amadeus.flightsearchengine.model.AirportModel;
import com.amadeus.flightsearchengine.par.AirportPar;
import org.springframework.stereotype.Repository;

@Repository
public class AirportDaoImpl extends EntityDAO<AirportModel, AirportPar, String> implements AirportDao
{
    @Override
    protected AirportPar convertToPar(AirportModel aInModel)
    {
        AirportPar lOutPar = new AirportPar();
        lOutPar.setId(aInModel.getId());
        lOutPar.setCity(aInModel.getCity());

        return lOutPar;
    }

    @Override
    protected AirportModel convertToModel(AirportPar aInPar)
    {
        AirportModel lOutModel = new AirportModel();
        lOutModel.setId(aInPar.getId());
        lOutModel.setCity(aInPar.getCity());

        return lOutModel;
    }

    @Override
    protected Class<AirportPar> getParClass()
    {
        return AirportPar.class;
    }
}
