package com.pathfinder.data.orient.object.repository;

import com.pathfinder.data.orient.commons.repository.OrientRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * The specific extension for {@link com.orientechnologies.orient.object.db.OObjectDatabaseTx} database.
 *
 * @param <T> the generic type to handle
 * @author Dzmitry_Naskou
 */
@NoRepositoryBean
public interface OrientObjectRepository<T> extends OrientRepository<T> {


}
