package com.titans.octopus.juc.questions.q1.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal：提供了线程局部变量。访问该变量的每个线程都有之间的局部变量（都有之间的值，线程隔离），独立于线程初始化的副本。<br/>
 * 内部使用ThreadLocalMap来维护线程局部变量。线程有个局部变量threadlocals = ThreadLocalMap;
 * map key : 当前变量（eg：threadInfo），value：当前线程的该变量的值
 * ThreadLocal的官方API解释为：<br/>
 *  “该类提供了线程局部 (thread-local) 变量。这些变量不同于它们的普通对应物，因为访问某个变量（通过其 get 或 set 方法）的每个线程都有自己的局部变量，它独立于变量的初始化副本。ThreadLocal 实例通常是类中的 private static 字段，它们希望将状态与某一个线程（例如，用户 ID 或事务 ID）相关联。”
 *  大概的意思有两点：<br/>
 *  ThreadLocal提供了一种访问某个变量的特殊方式：访问到的变量属于当前线程，即保证每个线程的变量不一样，而同一个线程在任何地方拿到的变量都是一致的，这就是所谓的线程隔离。<br/>
 *  如果要使用ThreadLocal，通常定义为private static类型，在我看来最好是定义为private static final类型。
 * @author vinfai
 * @since 2016/9/6
 */
public class MyThreadLocalExample {
    String a = "a";
    private /*static*/ ThreadLocal<String> threadInfo = new ThreadLocal<>();
    private ThreadLocal<String> threadInfo2 = new ThreadLocal<>();
    public static void main(String[] args) {
        MyThreadLocalExample myThreadLocalExample = new MyThreadLocalExample();
        MyThreadLocalExample myThreadLocalExample2 = new MyThreadLocalExample();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(()->{
            myThreadLocalExample.threadInfo.set(Thread.currentThread().getName()+"hello");

            myThreadLocalExample.threadInfo2.set("哈哈哈");
            System.out.println(Thread.currentThread().getName()+"-->"+myThreadLocalExample.threadInfo.get());
            //System.out.println(Thread.currentThread().getName()+"-->"+myThreadLocalExample.threadInfo2.get());
        });

       /* executorService.execute(()->{

            myThreadLocalExample.threadInfo.set(Thread.currentThread().getName()+"hello2222");
            System.out.println(Thread.currentThread().getName()+"-->"+myThreadLocalExample.threadInfo.get());
        });
        executorService.execute(()->{

            myThreadLocalExample2.threadInfo.set(Thread.currentThread().getName()+"222222");
            System.out.println(Thread.currentThread().getName()+"-->"+myThreadLocalExample2.threadInfo.get());
        });*/

        //System.out.println(Thread.currentThread().getName()+"2-->:"+ MyThreadLocalExample.threadInfo.get());

    }


}
