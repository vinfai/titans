package com.titans.octopus.synch;

/**
 * 内置锁的可重入
 * @author vinfai
 * @since 2017/6/29
 */
public class Widget {

    public synchronized void doSomething() {
        System.out.println("Widget do sth");

    }

    public void dosth2() {
        synchronized (this) {
            System.out.println("锁对象"+this);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" 锁对象 "+this);
        }
    }
}
