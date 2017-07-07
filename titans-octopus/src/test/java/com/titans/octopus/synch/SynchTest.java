package com.titans.octopus.synch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author vinfai
 * @since 2016/9/5
 */
public class SynchTest {

    static Object lock = new Object();

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        synchronized (lock) {
            System.out.println("synchronized block");
        }
        LoggingWidget widget = new LoggingWidget();

        service.execute(new Runnable() {
            @Override
            public void run() {
                widget.doSomething();
            }
        });
        service.execute(new Runnable() {
            @Override
            public void run() {
                widget.dolala();
            }
        });
        service.execute(new Runnable() {
            @Override
            public void run() {
                widget.dosth2();
            }
        });

        service.shutdown();

    }

    public static synchronized void doSth(String time) {
        System.out.println("synchronized method.");

    }
}
