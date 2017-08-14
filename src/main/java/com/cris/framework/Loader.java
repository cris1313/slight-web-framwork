package com.cris.framework;

import com.cris.framework.helper.BeanHelper;
import com.cris.framework.helper.ClassHelper;
import com.cris.framework.helper.ControllerHelper;
import com.cris.framework.helper.IocHelper;
import com.cris.framework.util.reflection.ClassUtil;

/**
 * Created by owen on 2017/8/14.
 */
public class Loader {
    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                //AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}
