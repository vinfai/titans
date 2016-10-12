package com.titans.web.action;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;

import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * spring controller 异步实现
 * @author vinfai
 * @since 2016/10/10
 */
@Controller
@RequestMapping("/async")
public class DeferredResultController {

    public DeferredResultController(){
        System.out.println(this);
    }

    @RequestMapping("/deferredResult/queue.xhtml")
    @ResponseBody
    public DeferredResult<String> deferredResult(){
        DeferredResult<String> result = new DeferredResult<>();
        System.out.println("request received");
        //简单的将每天请求放到队列中，其他（线程等）处理完设置结果并返回.
        deferredResultQueue.add(result);

        return result;
    }

    private static Queue<DeferredResult<String>> deferredResultQueue = new ConcurrentLinkedQueue<>();
    //定时任务，每2s调用一次
    @Scheduled(fixedRate = 2000)
    public void getDeferredResult() {
        System.out.println("size." + deferredResultQueue.size()+";"+this);
        for (DeferredResult<String> result : deferredResultQueue) {
            result.setResult("result " + new Date());
            this.deferredResultQueue.remove(result);
            System.out.println("request completed.");
        }
    }
}
