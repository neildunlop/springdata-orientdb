package com.pathfinder.data.orient.commons.repository.support;

/**
 * Created by IWC-NeilDunlop on 01/11/2016.
 */
public interface OrientStrategy<T> {

    <S extends T> S save(S entity);

    long count();

    String getSource();
}
