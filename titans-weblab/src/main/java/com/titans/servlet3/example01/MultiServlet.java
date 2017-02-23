package com.titans.servlet3.example01;

import com.titans.web.listener.MyAsyncListener;

import javax.servlet.AsyncContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * servlet3.0 async:<br/>
 * 1.open asyncSupported = true
 *
 * @author vinfai
 * @since 2016/9/27
 */
@WebServlet(name = "multiservlet",urlPatterns = {"/examples/multiservlet"},
        initParams = {@WebInitParam(name="country",value="hello")
                ,@WebInitParam(name="country2",value="hello2")}
                ,asyncSupported=true
        /*,loadOnStartup = 1*/
        )
public class MultiServlet extends HttpServlet /*implements SingleThreadModel*/{
    @Override
    public void init() throws ServletException {
        super.init();
        Enumeration<String> initParameterNames = this.getServletConfig().getInitParameterNames();
        String country = super.getServletConfig().getInitParameter("country");
        System.out.println(country);
        System.out.println(this.getServletName());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp); //error:HTTP Status 405 - HTTP method GET is not supported by this URL
        System.out.println("request "+req.getRequestURI());
        System.out.println("servletpath"+req.getServletPath());
        System.out.println("pathinfo :"+req.getPathInfo());
        System.out.println("contextPath:"+req.getContextPath());
        System.out.println("query string111:"+req.getQueryString());
//        resp.setCharacterEncoding("UTF-8");
        System.out.println(req.getParameter("name"));
        resp.setContentType("text/html;utf-8");
        resp.getWriter().println("servlet  do GET 中文测试");
        //resp.getWriter().flush();
        //1.启动异步
        AsyncContext asyncContext = req.startAsync();
//        asyncContext.getRequest().getAttribute("")
        //2.添加异步监听、设置请求超时时间
        asyncContext.addListener(new MyAsyncListener());
        asyncContext.setTimeout(10*1000);//可以设置超时测试 timeout<thread execute time.
        //3.异步任务执行，并在完成时调用complete() see Work.java
        new Thread(new Work(asyncContext)).start();
        /*ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Work(asyncContext));*/
        //测试部分返回
        resp.getWriter().println("主线程resp返回了！<br/>");
        resp.getWriter().flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        System.out.println(req.getParameter("hell"));
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public String getServletName() {
        return super.getServletName();
    }
}
