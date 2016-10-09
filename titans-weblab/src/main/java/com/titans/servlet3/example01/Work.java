package com.titans.servlet3.example01;

import javax.servlet.AsyncContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * servlet3业务工作线程
 * @author vinfai
 * @since 2016/9/27
 */
public class Work implements Runnable {
    private AsyncContext asyncContext;
    public Work(AsyncContext context) {
        this.asyncContext = context;
    }

    @Override
    public void run() {
        try {
            System.out.println("thread running");
            Thread.sleep(1000*4);
            PrintWriter writer = asyncContext.getResponse().getWriter();
            writer.println("异步返回任务");
            writer.println("aaaa异步返回完成");
            writer.flush();
            asyncContext.complete();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
