package com.titans.octopus.juc.questions.q1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 一个主线程下有多个子线程任务，主线程必须在10秒内将子线程执行的集合结果进行处理返回，子线程如果在100秒内没有执行完停止执行.<br/>
 * 使用executorService.invokeAll():会在超时为执行完成的情况下，抛出异常，
 * @author vinfai
 * @since 2016/8/16
 */
public class ThreadFork {

    private ExecutorService executorService = null;

    public List<String> forkResult(List<ForkCallback> callbackList, Long timeout){
        executorService = Executors.newFixedThreadPool(10);
        List<String> result = new ArrayList<>();
        try {
            //线程在timeout时间内未完成时，会执行future.cancel(true).@AbstractExecutorService.invokeAll
            //finally中执行了相关取消操作.
            List<Future<List<String>>> list = executorService.invokeAll(callbackList,timeout, TimeUnit.SECONDS);
            for (Future<List<String>> feature:list) {
                if(feature.isDone()){
//                    System.out.println("feature is done.");
                    try {
                        result.addAll(feature.get());
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch(CancellationException ingores){//线程在timeout时间内未完成时，future.get()会返回CancellationException
                        System.out.println("futureTask is cancelled.");
                    }
                }else if(feature.isCancelled()){
                    System.out.println("feature is cancelled.");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
        return result;
    }
}

class ForkCallback implements Callable<List<String>> {

    private Long sleepTime;//测试超时的情况

    public ForkCallback(Long sleepTime) {
        this.sleepTime = sleepTime;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public List<String> call() throws Exception {
        Thread.sleep(sleepTime*1000);
        List<String> list = new ArrayList<>(2);
        list.add(sleepTime.toString());
        list.add(sleepTime.toString());
        return list;
    }
}


