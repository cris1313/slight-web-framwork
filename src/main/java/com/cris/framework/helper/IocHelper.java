package com.cris.framework.helper;

import com.cris.framework.annotation.AutoInject;
import com.cris.framework.util.reflection.ReflectionUtil;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by owen on 2017/4/26.
 */
//实现依赖注入功能，为所有需要的对象（带有@AutoInject的对象）在特定的类里面设定值
public class IocHelper {
    //遍历来判断是否含有@AutoInject
    static{
        Map<Class<?>,Object> beanMaps = BeanHelper.getBeanMap();
        for (Map.Entry<Class<?>,Object> beanEntry:beanMaps.entrySet()){
            Field []fields = beanEntry.getKey().getDeclaredFields();
            if (!ArrayUtils.isEmpty(fields)){
            for(Field field:fields){
                //判断当前区域是否带有@AutoInject注解
                if(field.isAnnotationPresent(AutoInject.class)){
                    Object beanInstance = beanMaps.get(field.getType());
                    if (beanInstance!=null)
                    //注入
                    ReflectionUtil.setField(beanEntry.getKey(),field,beanInstance);
                }
            }
        }
        }
    }
}
