package com.titans.octopus.juc.questions.q1.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 解决ABA问题
 * @author vinfai
 * @since 2017/6/15
 */
public class AtomicStampedReferenceTest {

    public static AtomicInteger atomicInteger = new AtomicInteger(100);
    public static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(100, 0);

    public static void main(String[] args) throws InterruptedException {
//        CyclicBarrier
//        CountDownLatch
        Thread t1 = new Thread(()->{
            System.out.println("thread t1 start.");
            atomicInteger.compareAndSet(100, 101);
            atomicInteger.compareAndSet(101, 100);
            System.out.println("thread t1 executed.");
        });

        Thread t2 = new Thread(()->{
            System.out.println("thread t2 start.");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = atomicInteger.compareAndSet(100, 102);
            System.out.println(b);
        });
        /*t2.start();
        t1.start();*/


        Thread t3 = new Thread(()->{
            System.out.println("thread t3 start.");
            atomicStampedReference.compareAndSet(100, 101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            atomicStampedReference.compareAndSet(101, 100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println("thread t3 executed."+atomicStampedReference.getStamp());
        });

        Thread t4 = new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println("thread t4 start.stamp:"+stamp);
            //执行到这里，等待，t3执行完成！
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //stamp 已经变成2了
            System.out.println("new reference stamp:"+atomicStampedReference.getStamp());
            boolean b = atomicStampedReference.compareAndSet(100, 102,stamp,stamp+1);
            System.out.println("result:"+b);
        });
        t3.start();
        t4.start();
        t4.join();
        t3.join();

    }
}
