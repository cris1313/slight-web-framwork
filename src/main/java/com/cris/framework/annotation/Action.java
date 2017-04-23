package com.cris.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by owen on 2017/4/23.
 */
@Target(ElementType.METHOD)
public @interface Action {
    //servlet路径
    String value() default "/";
}
