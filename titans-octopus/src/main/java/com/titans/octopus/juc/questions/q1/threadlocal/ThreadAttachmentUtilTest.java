package com.titans.octopus.juc.questions.q1.threadlocal;

import com.google.common.collect.Maps;
import org.slf4j.instrumentation.ToStringHelper;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author vinfai
 * @since 2017/4/18
 */
public class ThreadAttachmentUtilTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(new ThreadAttachmentWork(10));
        executorService.submit(new ThreadAttachmentWork(100));
        executorService.submit(new ThreadAttachmentWork(1000));

    }


}

class ThreadAttachmentWork implements Runnable {
    private Integer count;
    public ThreadAttachmentWork(Integer data) {
        this.count = data;
    }
    @Override
    public void run() {
       for(;;) {
           Map<String,String> map = Maps.newHashMap();
           map.put("count", (count++)+"");

           ThreadAttachmentUtil.setThreadAttachment(map);
           try {
               Thread.sleep(2000L);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           System.out.println(ToStringHelper.render(ThreadAttachmentUtil.getThreadAttachment()));

       }
    }
}


