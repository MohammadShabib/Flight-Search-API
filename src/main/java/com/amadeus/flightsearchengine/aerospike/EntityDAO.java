package com.amadeus.flightsearchengine.aerospike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    /**
     * Converts a list of models to a list of pars
     *
     * @param aInModels the list of models
     * @return the list of pars
     */
    protected List<P> bulkConvertToPar(List<M> aInModels)
    {
        return aInModels.stream().map(this::convertToPar)
                .collect(Collectors.toList());
    }

    /**
     * Converts a list of pars to a list of models
     *
     * @param aInPars the list of pars
     * @return the list of models
     */
    protected List<M> bulkConvertToModel(List<P> aInPars)
    {
        return aInPars.stream().map(this::convertToModel)
                .collect(Collectors.toList());
    }

    public M create(M aInModel)
    {
        P lPar = convertToPar(aInModel);

        P lOutPar = database.persist(lPar);

        return convertToModel(lOutPar);
    }

    public List<M> createBulk(List<M> aInModel)
    {
        List<P> lPar = bulkConvertToPar(aInModel);

        List<P> lOutPar = database.persist(lPar);

        return bulkConvertToModel(lOutPar);
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

    public List<M> find(Function<P, Boolean> aInFunction)
    {
        List<P> lOutPar = database.find(getParClass(), aInFunction);

        return bulkConvertToModel(lOutPar);
    }

}
