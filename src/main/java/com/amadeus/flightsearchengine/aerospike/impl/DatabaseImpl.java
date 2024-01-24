package com.amadeus.flightsearchengine.aerospike.impl;


import com.aerospike.mapper.tools.AeroMapper;
import com.amadeus.flightsearchengine.par.AirportPar;
import com.amadeus.flightsearchengine.aerospike.DatabaseIf;
import org.springframework.stereotype.Service;

@Service
public class DatabaseImpl implements DatabaseIf
{
    private final AeroMapper aeroMapper;

    public DatabaseImpl(AeroMapper aInAeroMapper)
    {
        aeroMapper = aInAeroMapper;
    }

    @Override
    public <T> T persist(T aInObject)
    {
        aeroMapper.save(aInObject);
        return aInObject;
    }

    @Override
    public <T> T put(T aInObject)
    {
        aeroMapper.update(aInObject);
        return aInObject;
    }

    @Override
    public <T> T fetch(Class<T> aInClass, Object aInId)
    {
        return aeroMapper.read(aInClass, aInId);
    }

    @Override
    public <T> boolean delete(Class<T> aInClass, Object aInId)
    {
        return aeroMapper.delete(AirportPar.class, aInId);
    }
}