package com.cris.framework.util.reflection;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
    /**
     * 调用方法
     */
    public static Object invokeMethod(Object obj, Method method, Object... args) {
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(obj, args);
        } catch (Exception e) {
            LOGGER.error("invoke method failure", e);
            throw new RuntimeException(e);
        }
        return result;
    }
}
