package com.cris.framework.base.config;

import com.cris.framework.util.io.PropsUtil;

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

    public static String getAppJspPath(){
        return PropsUtil.getString(PROS,ConfigConstant.APP_JSP_PATH, "/WEB-INF/view/");
    }

    /**
     * 获取应用静态资源路径
     */
    public static String getAppAssetPath() {
        return PropsUtil.getString(PROS, ConfigConstant.APP_ASSET_PATH, "/asset/");
    }


}
