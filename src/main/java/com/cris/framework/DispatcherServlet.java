package com.cris.framework;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * Created by owen on 2017/3/27.
 */
public class DispatcherServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        //初始化工具类
        // TODO: 2017/3/27

        //获取ServletContext对象（用于注册Servlet)
        ServletContext servletContext = config.getServletContext();

        //注册处理JSP的Servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping("*");

        //注册处理静态资源的默认servlet
        // TODO: 2017/3/27
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

    }
}
