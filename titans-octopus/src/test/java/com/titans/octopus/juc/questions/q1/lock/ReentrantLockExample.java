package com.titans.octopus.juc.questions.q1.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock
 * @author vinfai
 * @since 2016/9/6
 */
public class ReentrantLockExample {

    int a = 0;
    //一个lock
    ReentrantLock lock = new ReentrantLock();

    public void writer() {
        lock.lock();
        try{
            a++;
            System.out.println("writer "+a);
        }finally {
            lock.unlock();
        }
    }

    public void reader() {
        lock.lock();
        try {
            int i = a;
            System.out.println("reader :"+i);
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(()->{
            for (int i = 0; i < 100; i++) {
                example.writer();
            }
        });
        executorService.execute(()->{
            for (int i = 0; i < 10000; i++) {
                example.reader();
            }
        });
        executorService.shutdown();
    }
}
