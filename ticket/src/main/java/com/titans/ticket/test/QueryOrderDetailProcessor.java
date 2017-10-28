package com.titans.ticket.test;

import com.titans.ticket.async.AsynchTask;
import com.titans.ticket.async.AsynchTaskProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * demo,订单成功后，异步去查询订单的详细信息
 * @author vinfai
 * @since 2017/2/23
 */
public class QueryOrderDetailProcessor implements AsynchTaskProcessor{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void processTask(AsynchTask asynchTask) {
        try {
            Thread.sleep(3000);//for test
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String orderno = (String) asynchTask.getInfo("tradeno");
        logger.warn("query order detial with orderno:"+orderno+";"+asynchTask.getAddtime());
//        HandlerInterceptorAdapter
//        BufferedReader
    }

//    AbstractTraceInterceptor
}

