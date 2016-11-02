package com.pathfinder.data.orient.object;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import com.pathfinder.data.orient.commons.core.OrientOperations;

public interface OrientObjectOperations extends OrientOperations<Object> {

    OObjectDatabaseTx getObjectDatabase();
}