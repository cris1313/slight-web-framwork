package com.cris.framework;

import com.cris.framework.bean.Handler;
import com.cris.framework.helper.ControllerHelper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by owen on 2017/3/27.
 */
@WebServlet(urlPatterns = "/*" ,loadOnStartup = 0)
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
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //获得请求路径和方法名
        String reqMethod = req.getMethod().toLowerCase();
        String reqPath = req.getPathInfo();
        Handler handler = ControllerHelper.getHandler(reqMethod,reqPath);
        if (handler!=null){
            //创建一个Map保存请求和请求的值
            Map<String,Object> paramMap = new HashMap<>();
            //http协议中，请求报文的主体部分body一般情况下是不用的
            //servlet只帮我们实现了没有帮我们实现获取body这部分内容，为了以防body里面会有内容
            //我们需要通过数据流来获取body部分的请求内容。
            Enumeration<String> paramName = req.getParameterNames();

            // TODO: 2017/7/6 思考dispatcher的设计 参照springmvc 
        }

    }
}
