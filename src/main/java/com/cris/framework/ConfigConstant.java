package com.cris.framework;
/**
 * Created by owen on 2017/4/25.
 */
public interface ConfigConstant {
    String CONFIG_FILE = "cris.properties";

    String JDBC_DRIVER = "cris.framework.jdbc.driver";
    String JDBC_URL = "cris.framework.jdbc.url";
    String JDBC_USERNAME = "cris.framework.jdbc.username";
    String JDBC_PASSWORD = "cris.framework.jdbc.password";

    //整个应用的基础包名
    String APP_BASE_PACKAGE = "cris.framework.app.base_package";
    //jsp路径
    String APP_JSP_PATH = "cris.framework.app.jsp_path";
    String APP_ASSET_PATH = "cris.framework.app.asset_path";
    String APP_UPLOAD_LIMIT = "cris.framework.app.upload_limit";
}
