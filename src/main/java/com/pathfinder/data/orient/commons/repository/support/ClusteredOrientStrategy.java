package com.pathfinder.data.orient.commons.repository.support;

import com.pathfinder.data.orient.commons.core.OrientOperations;
import com.pathfinder.data.orient.commons.repository.support.query.QueryUtils;

/**
 * Created by IWC-NeilDunlop on 01/11/2016.
 */
public class ClusteredOrientStrategy<T> implements OrientStrategy<T> {

    private final OrientOperations<T> operations;

    private final String cluster;

    public ClusteredOrientStrategy(OrientOperations operations, String cluster) {
        super();
        this.operations = operations;
        this.cluster = cluster;
    }

    @Override
    public <S extends T> S save(S entity) {
        return operations.save(entity, cluster);
    }

    @Override
    public long count() {
        return operations.countClusterElements(cluster);
    }

    @Override
    public final String getSource() {
        return QueryUtils.clusterToSource(cluster);
    }
}
