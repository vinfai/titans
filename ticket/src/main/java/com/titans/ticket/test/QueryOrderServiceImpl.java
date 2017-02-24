package com.titans.ticket.test;

import com.titans.ticket.async.AsynchTask;
import com.titans.ticket.async.AsynchTaskService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * demo 查询订单方法
 * @author vinfai
 * @since 2017/2/23
 */
@Service("queryOrderService")
public class QueryOrderServiceImpl implements InitializingBean {

    @Autowired@Qualifier("asynchTaskService")
    private AsynchTaskService asynchTaskService; //XML中定义
    AtomicInteger ai = null;
    @Override
    public void afterPropertiesSet() throws Exception {
        asynchTaskService.registerProcessor("QUERYORDER",new QueryOrderDetailProcessor());
         ai = new AtomicInteger(0);
    }

    public void queryOrderDetail(String tradeno) {
        AsynchTask task = new AsynchTask("QUERYORDER", tradeno, 10);
        task.addInfo("tradeno", tradeno);
        asynchTaskService.addTask(task);

    }


    @Scheduled(fixedRate=1000)
    public void queryOrderJob(){
        queryOrderDetail(ai.incrementAndGet()+"");
        System.out.println(asynchTaskService.getExecutorActiveCount()+";"+asynchTaskService.getQueueSize());

    }
}
