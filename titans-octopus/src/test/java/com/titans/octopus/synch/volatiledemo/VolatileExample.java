package com.titans.octopus.synch.volatiledemo;

/**
 * 测试volatile的效果，避免在线程中进行任何操作，这些都有可能导致线程重读主内存数据到线程副本！
 * @author vinfai
 * @since 2016/9/5
 */
public class VolatileExample {
    //成员变量
    public volatile boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        VolatileExample volatileTest = new VolatileExample();
        VolatileThread thread = new VolatileThread(volatileTest);
        VolatileThread thread2 = new VolatileThread(volatileTest);
        thread.start();
        thread2.start();
        Thread.sleep(2000);
        volatileTest.flag = false;
        System.out.println(volatileTest.flag);

    }
}

class VolatileThread extends Thread{
    private VolatileExample t;
    public VolatileThread(VolatileExample volatileTest) {
        this.t = volatileTest;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" is running.");
        while (t.flag){
           /*System.out.println(Thread.currentThread().getName()+" value is ");
           try {
           //sleep操作会对变量进行主内存读，无法模拟副本变量与主内存不一致现象。
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }*/

       }
        System.out.println(Thread.currentThread().getName()+" is end.");
    }
}

