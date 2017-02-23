package com.titans.ticket.async;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 异步任务接口
 * @author vinfai
 * @since 2017/2/22
 */
public class AsynchTask {

    private long validtime;//有效时间，用于优化任务

    private String tasktype;//任务类型

    private String taskUKey;//任务唯一值

    private long addtime;//添加时间

    //need lock

    private Map otherinfo = Maps.newHashMap();//其他信息，如传递的参数

    public AsynchTask(String tasktype, String taskUKey , int maxWaitSeconds) {
        this.validtime = System.currentTimeMillis()+maxWaitSeconds*1000;
        this.tasktype = tasktype;
        this.taskUKey = taskUKey;
        this.addtime = System.currentTimeMillis();
    }

    public void addInfo(String key , Object object) {
        this.otherinfo.put(key, object);
    }

    public Object getInfo(String key) {
        return this.otherinfo.get(key);
    }

    //判断任务是否超时
    public boolean isTimeout(){
        return this.validtime < System.currentTimeMillis();
    }

    public String getTasktype() {
        return tasktype;
    }

    public void setTasktype(String tasktype) {
        this.tasktype = tasktype;
    }

    public String getTaskUKey() {
        return taskUKey;
    }

    public void setTaskUKey(String taskUKey) {
        this.taskUKey = taskUKey;
    }


}
