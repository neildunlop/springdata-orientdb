package com.pathfinder.data.orient.commons.repository.annotation;

import com.pathfinder.data.orient.commons.repository.DetachMode;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface Detach {

    DetachMode value();
}
