package com.cris.framework.bean;

import java.lang.reflect.Method;

/**
 * Created by owen on 2017/7/6.
 */
public class Handler {
    private Class<?> controllerClass;

    private Method actionMethod;

    public Handler(Class<?> controllerClass,Method actionMethod){
        this.actionMethod=actionMethod;;
        this.controllerClass=controllerClass;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public void setControllerClass(Class<?> controllerClass) {
        this.controllerClass = controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }

    public void setActionMethod(Method actionMethod) {
        this.actionMethod = actionMethod;
    }

}
