package com.cris.framework;

import com.cris.framework.base.bean.Data;
import com.cris.framework.base.bean.Handler;
import com.cris.framework.base.bean.Param;
import com.cris.framework.base.bean.View;
import com.cris.framework.base.config.ConfigHelper;
import com.cris.framework.helper.BeanHelper;
import com.cris.framework.helper.ControllerHelper;
import com.cris.framework.util.RequestUtil;
import com.cris.framework.util.commons.StringUtil;
import com.cris.framework.util.io.JsonUtil;
import com.cris.framework.util.reflection.ReflectionUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by owen on 2017/3/27.
 */
@WebServlet(urlPatterns = "/*" ,loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {

        //获取ServletContext对象（用于注册Servlet)
        ServletContext servletContext = config.getServletContext();

        Loader.init();


        registerServlet(servletContext);
        //注册处理静态资源的默认servlet
        // TODO: 2017/3/27
    }

    private void registerServlet(ServletContext servletContext) {
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping("/index.jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");

        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping("/favicon.ico");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // TODO: 2017/8/14 servlet 初始化 
        //获得请求路径和方法名
        String reqMethod = req.getMethod().toLowerCase();
        String reqPath = req.getPathInfo();
        Handler handler = ControllerHelper.getHandler(reqMethod,reqPath);
        if (handler!=null){
            //获取controller类和他的实例
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);

            //创建一个Map保存请求和请求的值
            Map<String,Object> paramMap = new HashMap<>();
            //http协议中，请求报文的主体部分body一般情况下是不用的
            //servlet只帮我们实现了没有帮我们实现获取body这部分内容，为了以防body里面会有内容
            //我们需要通过数据流来获取body部分的请求内容。
            Param param = RequestUtil.createParam(req);
            Object result;
            //param可以有参也可以无参
            if (param.isEmpty()){
                result = ReflectionUtil.invokeMethod(controllerBean,handler.getActionMethod());
            }else {
                result = ReflectionUtil.invokeMethod(controllerBean,handler.getActionMethod(),param);
            }
            //如果是根据Action方法的返回值决定如何处理
            if (result instanceof View){
                handleView((View)result,res,req);
            }else  if (result instanceof Data){
                handleData(res,(Data)result);
            }

        }

    }
    public void handleView(View view,HttpServletResponse response,HttpServletRequest request) throws IOException, ServletException {
        String path = view.getPath();
        if (StringUtil.isNotEmpty(path)) {
            if (path.startsWith("/")) {
                response.sendRedirect(request.getContextPath() + path);
            } else {
                Map<String, Object> model = view.getModel();
                for (Map.Entry<String, Object> entry : model.entrySet()) {
                    request.setAttribute(entry.getKey(), entry.getValue());
                }
                request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
            }
        }
    }
    private void handleData(HttpServletResponse response,Data data) throws IOException {
        Object model = data.getModel();
        if (model != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            String json = JsonUtil.toJson(model);
            writer.write(json);
            writer.flush();
            writer.close();
        }
    }

}
