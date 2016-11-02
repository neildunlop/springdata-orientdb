package com.pathfinder.data.orient.commons.repository.support;

import com.pathfinder.data.orient.commons.core.OrientOperations;
import com.pathfinder.data.orient.commons.repository.support.query.QueryUtils;

/**
 * Created by IWC-NeilDunlop on 01/11/2016.
 */
public class SimpleOrientStrategy<T> implements OrientStrategy<T> {

    private final OrientOperations<T> operations;

    private final Class<T> domainClass;

    public SimpleOrientStrategy(OrientOperations operations, Class<T> domainClass) {
        super();
        this.operations = operations;
        this.domainClass = domainClass;
    }

    @Override
    public <S extends T> S save(S entity) {
        return operations.save(entity);
    }

    @Override
    public long count() {
        return operations.countClass(domainClass);
    }

    @Override
    public String getSource() {
        return QueryUtils.toSource(domainClass);
    }
}
