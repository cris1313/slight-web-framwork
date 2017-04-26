package com.cris.framework.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * Created by owen on 2017/4/26.
 */
public class ReflectionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);
    public static Object getNewInstance(Class<?> cl){
        Object instance;
        try{
            instance = cl.newInstance();
        }catch (Exception e){
            LOGGER.error("new instance failure",e);
            throw new RuntimeException();
        }
        return instance;
    }
    public static void setField(Object o, Field field, java.lang.Object value){
        try{
            field.setAccessible(true);
            field.set(o,value);
        }catch (Exception e){
            LOGGER.error("set field error",e);
            throw new RuntimeException();
        }
    }
}
