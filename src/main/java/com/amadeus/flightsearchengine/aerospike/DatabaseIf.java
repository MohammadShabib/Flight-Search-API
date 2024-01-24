package com.amadeus.flightsearchengine.aerospike;

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
     * Removes an object from the DB.
     *
     * @param aInClass the object's class
     * @param aInId    the object's ID
     * @return true if the record existed, false if not
     */
    <T> boolean delete(Class<T> aInClass, Object aInId);
}