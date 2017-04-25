package com.cris.framework.helper;

import com.cris.framework.ConfigConstant;
import com.cris.framework.util.PropsUtil;

import java.util.Properties;

/**
 * Created by owen on 2017/4/25.
 */
public final class ConfigHelper {
    private static final Properties PROS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);
    //返回应用的基础包名
    public static String getAppBasePackage(){
        return PropsUtil.getString(PROS,ConfigConstant.APP_BASE_PACKAGE);
    }
}
