package com.pathfinder.data.orient.commons.repository;

/**
 * Abstract interface for Orient source (cluster or class) information.
 *
 * @author Dzmitry_Naskou
 */
public interface OrientSource {

    /**
     * Gets the source type.
     *
     * @return the source type
     */
    SourceType getSourceType();

    /**
     * Gets the source name.
     *
     * @return the name
     */
    String getName();
}
