package com.titans.web.listener;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * asynch listener异步监听
 * @author vinfai
 * @since 2016/10/9
 */

public class MyAsyncListener implements AsyncListener{

    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        ServletResponse response = event.getAsyncContext().getResponse();
        response.getWriter().println("<br/>任务完成，listener.onComplete invoked!");
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        ServletResponse response = event.getAsyncContext().getResponse();
        response.getWriter().println("<br/>request is timeout.Listener.timeout is invoked!");
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        ServletResponse response = event.getAsyncContext().getResponse();
        response.getWriter().println("<br/>request is listener error  invoked! 403");
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        System.out.println("start async ");
    }
}
