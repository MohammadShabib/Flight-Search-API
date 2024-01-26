package com.amadeus.flightsearchengine.aerospike;

import java.util.List;
import java.util.function.Function;

public interface DatabaseIf
{
    /**
     * Persists an object in the DB.
     *
     * @param aInObject the object to persist
     * @param <T>       the object type
     * @return the persisted object, or the original object if it was modified
     * in-place
     */
    <T> T persist(T aInObject);

    /**
     * Performs a batch persist on a list of DB entities of the same type.<br/>
     *
     * @param aInObjects the objects to persist
     * @param <T>        should be a database type (Par)
     * @return the IDs of the failed records
     */
    <T> List<T> persist(List<T> aInObjects);

    /**
     * Puts an object in the DB; equivalent to a persist call if the object
     * doesn't already exist, or an update call if it does.  This may not be
     * used for versioned entities.
     *
     * @param aInObject the object to persist
     * @param <T>       the object type
     * @return the persisted object, or the original object if it was modified
     * in-place
     */
    <T> T put(T aInObject);

    /**
     * Fetches an object from the DB.
     *
     * @param aInClass the object's class
     * @param aInId    the object's ID
     * @param <T>      the object's type
     * @return the object, or null if no such object was found
     */
    <T> T fetch(Class<T> aInClass, Object aInId);

    /**
     * Fetches All Object of a given Entity from DM.
     *
     * @param aInClass the object's class
     * @return List of all objects
     */
    <T> List<T> fetchAll(Class<T> aInClass);

    /**
     * Removes an object from the DB.
     *
     * @param aInClass the object's class
     * @param aInId    the object's ID
     * @return true if the record existed, false if not
     */
    <T> boolean delete(Class<T> aInClass, Object aInId);

    /**
     * @param aInClass    the object's class
     * @param aInFunction The Criteria Function
     * @param <T>         the object's type
     * @return List of objects that meets the given criteria
     */
    <T> List<T> find(Class<T> aInClass, Function<T, Boolean> aInFunction);
}
