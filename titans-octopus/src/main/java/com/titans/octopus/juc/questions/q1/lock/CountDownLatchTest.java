package com.titans.octopus.juc.questions.q1.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch 原理：
 * @author vinfai
 * @since 2017/4/18
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(2);
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(()->{
            doCountDown(latch);
        });
        executorService.submit(()->{
            doCountDown(latch);
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }

    private static void doCountDown(CountDownLatch latch) {
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }
}


