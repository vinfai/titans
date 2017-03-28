package com.titans.ticket.async;

import java.util.Map;

/**
* 异步任务调度模板，为每个业务配置一个线程池，业务处理类及其他统计信息<br/>
 * 功能：增加、取消任务，
* 扩展：日志的统计、线程池监控统计等
* @author vinfai
* @since 2017/2/23
*/
public interface AsynchTaskService {

    /**
     * 添加具体任务
     * @param task
     */
    void addTask(AsynchTask task);

    /**
     * 等待waitTimeSecond秒或凑齐minCount才一起执行
     * @param task
     * @param waitTimeSecond
     * @param minCount
     */
    void addTask(AsynchTask task, int waitTimeSecond, int minCount);

    /**
     * 注册具体业务处理器
     * @param tasktype 任务类型
     * @param processor 业务处理器
     */
    void registerProcessor(String tasktype,AsynchTaskProcessor processor);

    int getExecutorActiveCount();

    Map<String, Integer> getTaskQueueMap();

    int getQueueSize();

}
