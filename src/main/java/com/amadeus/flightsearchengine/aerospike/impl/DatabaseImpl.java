package com.amadeus.flightsearchengine.aerospike.impl;


import com.aerospike.mapper.tools.AeroMapper;
import com.amadeus.flightsearchengine.aerospike.DatabaseIf;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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
    public <T> List<T> persist(List<T> aInObjects)
    {
        aInObjects.forEach(this::persist);
        return aInObjects;
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
    public <T> List<T> fetchAll(Class<T> aInClass)
    {
       return aeroMapper.scan(aInClass);
    }

    @Override
    public <T> boolean delete(Class<T> aInClass, Object aInId)
    {
        return aeroMapper.delete(aInClass, aInId);
    }

    @Override
    public <T> List<T> find(Class<T> aInClass, Function<T, Boolean> aInFunction)
    {
        List<T> lOutResult = new ArrayList<T>();
        Function<T, Boolean> lFunction = a -> {
            if (aInFunction.apply(a))
            {
                lOutResult.add(a);
            }
            return true;
        };
        aeroMapper.find(aInClass, lFunction);
        return lOutResult;
    }
}