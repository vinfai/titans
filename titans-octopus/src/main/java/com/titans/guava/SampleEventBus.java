package com.titans.guava;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 事件总线
 * @author vinfai
 * @since 2017/7/28
 */
public class SampleEventBus {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus("sample");
        eventBus.register(new MyEvent());
        //eventBus.post("hello world.");
        ExecutorService service = Executors.newFixedThreadPool(10);

        service.submit(new Work(eventBus));
        service.submit(new Work(eventBus));
        service.submit(new Work(eventBus));
        service.submit(new Work(eventBus));
        service.submit(new Work(eventBus));
        service.submit(new Work(eventBus));
        service.submit(new Work(eventBus));
        service.submit(new Work(eventBus));
        service.submit(new Work(eventBus));
//        service.submit();

       /* System.out.println(Thread.currentThread().getName());
        EventBus eventBus1 = new AsyncEventBus(Executors.newFixedThreadPool(3));
        eventBus1.register(new MyEvent());
        eventBus1.post("hello world2");
        eventBus1.post(1L);*/
    }
}


class Work implements  Runnable{

    public EventBus eventBus;

    public Work(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void run() {
        eventBus.post("haha" + new Random().nextInt(10));
    }
}

class MyEvent {

    @Subscribe
    public void sub(String message) {
        System.out.println(Thread.currentThread().getName()+":"+message);
    }

    @Subscribe
    public void addPoint(Long aa) {
        System.out.println(Thread.currentThread().getName()+"add point "+ aa);
    }
}
