package com.titans.octopus.concurrent.synch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 
 * @author vinfai
 * @since 2016-09-04
 */
public class VolatileDemo {


    private static ExecutorService executor;

    static {
        executor = Executors.newFixedThreadPool(10);
    }

    public static void main(String[] args) {
        VoliatileRunnable voliatileRunnable = new VoliatileRunnable();
        VoliatileRunnable voliatileRunnable2 = new VoliatileRunnable();
        Thread thread1 = new Thread(voliatileRunnable);
        Thread thread2 = new Thread(voliatileRunnable2);
        //executor.execute(thread1);
        //executor.execute(thread2);
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //线程也直接停了。为什么？
        //voliatileRunnable.setRunning(false);
        System.out.println("主内存中runnable:" + voliatileRunnable.isRunning());
        //executor.shutdown();
    }
}

class VoliatileRunnable implements Runnable{

    private boolean isRunning = true;
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"is inner.");
        while (isRunning) {
            System.out.println(Thread.currentThread().getName() + " is running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+" is end.");
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public boolean isRunning() {
        return isRunning;
    }
}

