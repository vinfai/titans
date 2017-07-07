package com.titans.octopus.synch;

/**
 * @author vinfai
 * @since 2017/6/29
 */
public class LoggingWidget extends Widget {
    private Object lock = new Object();
    @Override
    public synchronized void doSomething() {
        System.out.println(this+"锁 LoggingWidget 对象dosth");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        super.doSomething();
        System.out.println(this+"锁 LoggingWidget 对象end!");
    }

    public void dolala() {
        synchronized (lock) {
            System.out.println(this+"锁lock对象");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this+"锁lock对象 end!");
        }
    }
}
