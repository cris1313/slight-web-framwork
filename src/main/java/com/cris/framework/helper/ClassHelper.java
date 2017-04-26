package com.cris.framework.helper;

import com.cris.framework.annotation.Controller;
import com.cris.framework.annotation.Service;
import com.cris.framework.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by owen on 2017/4/25.
 */
public final class ClassHelper {
    private static final Set<Class<?>> CLASS_SET;
    static {
        //获取基础包名，需要从配置文件读取
        String basePackage  = ConfigHelper.getAppBasePackage();
        //获取基础包名下的所有类
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }
    //获取应用包名下的所有类
    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }
    //获取带有Controller注解的所有类
    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> controllerSet = new HashSet<Class<?>>();
        for(Class cl:CLASS_SET){
            if(cl.isAnnotationPresent(Controller.class))
                controllerSet.add(cl);
        }
        return controllerSet;
    }

    //获取带有Service注解的所有类
    public static Set<Class<?>> getServiceClassSet(){
        Set<Class<?>> serviceSet = new HashSet<Class<?>>();
        for(Class cl: CLASS_SET){
            if(cl.isAnnotationPresent(Service.class))
                serviceSet.add(cl);
        }
        return serviceSet;
    }

    //获取包名下的所有bean类，把service和controller当作bean
    public static Set<Class<?>> getBeanSet(){
        Set<Class<?>> beanSet = new HashSet<Class<?>>();
        beanSet.addAll(getControllerClassSet());
        beanSet.addAll(getServiceClassSet());
        return beanSet;
    }
}
