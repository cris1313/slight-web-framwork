package com.cris.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by owen on 2017/5/31.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
   Class<? extends Annotation> value();
}
