package com.amadeus.flightsearchengine.aerospike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Base class for DAO classes. Provides default implementations for simple DB operations
 *
 * @param <M> the Model type
 * @param <P> the Par type
 * @param <I> the type of the Primary Key
 */
@Repository
public abstract class  EntityDAO<M, P, I>
{
    @Autowired
    protected DatabaseIf database;

    /**
     * Converts the model object to its corresponding par object
     *
     * @param aInModel the model to convert
     * @return the converted par
     */
    protected abstract P convertToPar(M aInModel);

    /**
     * Converts the par object to its corresponding par model
     *
     * @param aInPar the par to convert
     * @return the converted model
     */
    protected abstract M convertToModel(P aInPar);

    protected abstract Class<P> getParClass();

    public M create(M aInModel)
    {
        P lPar = convertToPar(aInModel);

        P lOutPar = database.persist(lPar);

        return convertToModel(lOutPar);
    }

    public M update(M aInModel)
    {
        P lPar = convertToPar(aInModel);

        P lOutPar = database.put(lPar);

        return convertToModel(lOutPar);
    }

    public M readById(I aInId)
    {
        P lPar = database.fetch(getParClass(), aInId);

        return lPar == null
                ? null
                : convertToModel(lPar);
    }

    public boolean delete(I aInId)
    {
        return database.delete(getParClass(), aInId);
    }

}
