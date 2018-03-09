package com.titans.octopus.thread.cp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * repertory
 *
 * @author fangwenhui
 * @date 2018-03-09 10:27
 **/
public class WareHouse {

    private Object lock = new Object();

    private volatile List<String> list;

    private Integer capacity ;

    public WareHouse(Integer capacity) {
        this.capacity = capacity;
        list = new ArrayList<>(capacity);
    }


    public void productFood() {
        while (true){
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " product get lock");
                try {
                    //需要使用循环等待，避免notifyAll同时唤醒多个线程时候需要去校验是否可以继续往下执行。
                    while (list.size() == capacity) {
                        System.out.println(Thread.currentThread().getName()+"; full now. wait product");
                        lock.wait();
                    }
                    String food = "food" + new Random().nextInt();
                    list.add(food);
                    System.out.println(Thread.currentThread().getName()+"product food now."+food+";notify consumer."+list.size());
                    lock.notifyAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    public void consumerFood() {
        String threadName = Thread.currentThread().getName();
        while (true) {
            synchronized (lock) {
                System.out.println(threadName + " get lock");
                try {
                    while (list.size() == 0) {
                        System.out.println(threadName+" no food now. wait.");
                        lock.wait();
                    }
                    System.out.println(threadName+";weak now."+new Date());
                    if (list.size() == 0) {
                        System.out.println( threadName+ " foot is 0");
                    }
                    String food = list.remove(0);
                    System.out.println(Thread.currentThread().getName()+"eat food "+food+";notify productor"+list.size());

                    lock.notifyAll();
                } catch (Exception e) {
                    lock.notifyAll();
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public void test() {
        synchronized (lock) {
            System.out.println("get lock and wait.");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("weakup from notify");
        }
    }


    public void notify2() {
        synchronized (lock) {
            System.out.println("get lock and notify2.");
            lock.notify();

        }
    }


}
