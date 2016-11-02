package com.pathfinder.data.orient.commons.repository.query;

import com.pathfinder.data.orient.commons.repository.OrientSource;
import org.springframework.data.repository.query.ParameterAccessor;

public interface OrientParameterAccessor extends ParameterAccessor {

    OrientSource getSource();
}
