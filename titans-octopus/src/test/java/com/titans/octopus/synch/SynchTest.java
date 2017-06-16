package com.titans.octopus.synch;

/**
 * @author vinfai
 * @since 2016/9/5
 */
public class SynchTest {

    static Object lock = new Object();

    public static void main(String[] args) {
        synchronized (lock) {
            System.out.println("synchronized block");
        }
    }

    public static synchronized void doSth(String time) {
        System.out.println("synchronized method.");

    }
}
