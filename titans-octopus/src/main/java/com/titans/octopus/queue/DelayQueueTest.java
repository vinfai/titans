package com.titans.octopus.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列<br/>
 * 应用场景：订单过10分钟后自动取消！过N分钟后执行一个操作！<br/>
 * 考试120分钟自动提交，缓存N分钟失效检查
 *
 * @author vinfai
 * @since 2016/12/29
 */
public class DelayQueueTest {

    //private static Logger logger = LoggerFactory.getLogger(DelayQueueTest.class);

    public static void main(String[] args) {
        DelayQueue<OrderCancelDelay> queue = new DelayQueue<>();
        OrderCancelDelay orderCancelDelay = new OrderCancelDelay("order1",5L);
        OrderCancelDelay orderCancelDelay2 = new OrderCancelDelay("order2",2L);
        OrderCancelDelay orderCancelDelay3 = new OrderCancelDelay("order3",10L);
        queue.add(orderCancelDelay);
        queue.add(orderCancelDelay2);
        queue.add(orderCancelDelay3);

        while (queue.size()>0){
            OrderCancelDelay order = queue.poll();
            if(order ==null){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                //OrderCancelDelay order = queue.poll();
                //logger.info("get order :{}"+order);
                System.out.println(queue.size()+"get order :"+order);
            }
        }

    }
}

class OrderCancelDelay implements Delayed{
    private String tradeno;
    private Long delayTime ;
    private Long expireTime ;
    public OrderCancelDelay(String tradeno,Long delayTime) {
        this.delayTime = delayTime;
        this.tradeno = tradeno;
        this.expireTime = TimeUnit.NANOSECONDS.convert(this.delayTime,TimeUnit.SECONDS)+System.nanoTime();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        //延迟时间
        return unit.convert(expireTime-System.nanoTime(),TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if(o==null||!(o instanceof OrderCancelDelay)){
            return 1;
        }
        if(o == this){
            return 0;
        }
        OrderCancelDelay delay = (OrderCancelDelay)o;
        return this.delayTime-delay.getDelayTime()>0?1:-1;
    }

    @Override
    public String toString() {
        return tradeno+";delay time is "+delayTime+"s";
    }

    public String getTradeno() {
        return tradeno;
    }

    public void setTradeno(String tradeno) {
        this.tradeno = tradeno;
    }

    public Long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(Long delayTime) {
        this.delayTime = delayTime;
    }
}
