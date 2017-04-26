package com.cris.framework.helper;

import com.cris.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by owen on 2017/4/26.
 */
//这个助手类用于提供对象和实例之间的映射
public class BeanHelper {
    //bean_map用于存储对象和实例的映射
    private static Map<Class<?>,Object> BEAN_MAP = new HashMap<>();
    //为每个类创建实例
    static{
        Set<Class<?>> BEAN_SET = ClassHelper.getBeanSet();
        for(Class<?> cl:BEAN_SET){
            BEAN_MAP.put(cl, ReflectionUtil.getNewInstance(cl));
        }
    }
    //获取beanmap
    public static Map<Class<?>,Object> getBeanMap(){
        return BEAN_MAP;
    }




}
