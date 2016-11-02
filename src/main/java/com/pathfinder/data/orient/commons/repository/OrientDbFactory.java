package com.pathfinder.data.orient.commons.repository;

import com.orientechnologies.orient.core.db.ODatabase;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;

/**
 * Created by IWC-NeilDunlop on 01/11/2016.
 */
public interface OrientDbFactory {

    /**
     * Creates a default {@link ODatabase} instance.
     *
     * @return
     * @throws DataAccessException
     */
    ODatabase getDb() throws DataAccessException;

    /**
     * Creates a {@link ODatabase} instance to access the database with the given name.
     *
     * @param dbName must not be {@literal null} or empty.
     * @return
     * @throws DataAccessException
     */
    ODatabase getDb(String dbName) throws DataAccessException;

    /**
     * Exposes a shared {@link OrientExceptionTranslator}.
     *
     * @return will never be {@literal null}.
     */
    PersistenceExceptionTranslator getExceptionTranslator();
}
