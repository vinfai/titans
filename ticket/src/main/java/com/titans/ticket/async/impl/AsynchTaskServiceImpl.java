package com.titans.ticket.async.impl;

import com.google.common.collect.Maps;
import com.titans.ticket.async.AsynchTask;
import com.titans.ticket.async.AsynchTaskProcessor;
import com.titans.ticket.async.AsynchTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 异步任务调度模板，为每个业务配置一个线程池，业务处理类及其他统计信息<br/>
 * 扩展：日志的统计、线程池监控统计等;
 * 分布式环境下，有些要加分布式锁，避免重复执行？
 * @author vinfai
 * @since 2017/2/23
 */
public class AsynchTaskServiceImpl implements AsynchTaskService,InitializingBean{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<String,AsynchTaskProcessor> processorMap = Maps.newConcurrentMap();

    private Map<String,AtomicInteger> putCountMap = Maps.newHashMap();

    private Map<String,AtomicInteger> completeCountMap = Maps.newHashMap();

    private ThreadPoolExecutor executor;//线程池

    private int maxnumPoolSize = 60;

    public void setMaxnumPoolSize(int maxnumPoolSize) {
        this.maxnumPoolSize = maxnumPoolSize;
    }

    public AsynchTaskServiceImpl() {
        logger.info("AsynchTaskServiceImpl.............");
        System.out.println("AsynchTaskServiceImpl322222222222");
    }

    @Override
    public void addTask(AsynchTask task) {
        if(!task.isTimeout()){
            putCountMap.get(task.getTasktype()).incrementAndGet();
            AsynchTaskProcessor processor = processorMap.get(task.getTasktype());
            TaskWorkThread taskWorkThread = new TaskWorkThread(task, processor);
            executor.execute(taskWorkThread);
            logger.info("executor inited."+executor.getCorePoolSize());

        }
    }

    @Override
    public void addTask(AsynchTask task, int waitTimeSecond, int minCount) {
        throw new RuntimeException("unsupported now.");
    }

    @Override
    public void registerProcessor(String tasktype,AsynchTaskProcessor processor) {
        //注册处理器，初始化该处理器的统计信息
        processorMap.put(tasktype, processor);
        putCountMap.put(tasktype, new AtomicInteger(0));
        completeCountMap.put(tasktype, new AtomicInteger(0));
    }

    @Override
    public Map<String, Integer> getTaskQueueMap() {
        Map<String, Integer> result = new HashMap<String, Integer>();
        for(Map.Entry<String, AtomicInteger> entry: putCountMap.entrySet()){
            String type = entry.getKey();
            int complete = completeCountMap.get(type).get();
            int queue = entry.getValue().get() - complete;
            result.put(type, queue);
            result.put(type+"Complete", complete);
        }
        return result;
    }

    @Override
    public int getExecutorActiveCount(){
        return executor.getActiveCount();
    }

    @Override
    public int getQueueSize(){
        return  executor.getQueue().size();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //TODO 优化：线程池改为统一管理配置，包括修改参数、监控报警等
        //Initializes the thread pool
        BlockingQueue queue = new LinkedBlockingQueue(100);
        executor = new ThreadPoolExecutor(maxnumPoolSize, maxnumPoolSize, 60, TimeUnit.SECONDS, queue, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread("");
            }
        });
        System.out.println("executor....");
    }

    class TaskWorkThread implements Runnable{

        private AsynchTask asynchTask;

        private AsynchTaskProcessor processor;

        public TaskWorkThread(AsynchTask asynchTask, AsynchTaskProcessor processor) {
            this.asynchTask = asynchTask;
            this.processor = processor;
        }

        @Override
        public void run() {
            try {
                processor.processTask(asynchTask);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //完成统计
                int count = completeCountMap.get(asynchTask.getTasktype()).incrementAndGet();
                if(count%100==0){
                    //TODO addMonitor
                    logger.warn("complete count :" + count);
                }
            }
        }
    }


}
