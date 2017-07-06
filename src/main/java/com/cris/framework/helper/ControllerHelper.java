package com.cris.framework.helper;

import com.cris.framework.annotation.Action;
import com.cris.framework.bean.Handler;
import com.cris.framework.bean.Request;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by owen on 2017/7/6.
 */
public final class ControllerHelper {
    //用一个Map存放请求和处理方法之间的映射
    private static final HashMap<Request,Handler> ACTION_MAP = new HashMap<>();
    //初始化这个Map
    static {
        //扫描controller类获取action注解
        Set<Class<?>> controllerSet = ClassHelper.getControllerClassSet();

        if (!controllerSet.isEmpty()){
            for (Class<?> ctrclass:controllerSet) {
                Method[] methods = ctrclass.getDeclaredMethods();
                if (methods.length!=0){
                    for (Method method:methods){
                        //判断是否有action注解
                        if (method.isAnnotationPresent(Action.class)){
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            if (mapping.matches("\\w+:/\\w*")){
                                String[] array = mapping.split(":");
                                if (array.length==2){
                                    String reqMethod = array[0];
                                    String reqPath = array[1];
                                    Request request = new Request(reqMethod,reqPath);
                                    Handler handler = new Handler(ctrclass,method);
                                    ACTION_MAP.put(request,handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public static Handler getHandler(String reqMethod,String reqPath){
        Request request = new Request(reqMethod,reqPath);
        return ACTION_MAP.get(request);
    }
}
