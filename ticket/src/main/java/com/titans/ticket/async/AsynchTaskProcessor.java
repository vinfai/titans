package com.titans.ticket.async;

/**
 * 业务处理类
 * @author vinfai
 * @since 2017/2/23
 */
public interface AsynchTaskProcessor<T extends AsynchTask> {

    //处理具体业务
    void processTask(T asynchTask);

    //锁相关？？
}
