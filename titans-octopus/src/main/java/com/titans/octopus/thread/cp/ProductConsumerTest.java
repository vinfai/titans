package com.titans.octopus.thread.cp;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author fangwenhui
 * @date 2018-03-09 10:43
 **/
public class ProductConsumerTest {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10/*, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread("hahah"+new Random().nextInt(100));
            }
        }*/);

        WareHouse wareHouse = new WareHouse(4);
       /* executor.execute(new Productor(wareHouse));*/
        executor.execute(new Productor(wareHouse));
       executor.execute(new Productor(wareHouse));
        executor.execute(new Consumer(wareHouse));
        executor.execute(new Consumer(wareHouse));
        //executor.execute(new Consumer(wareHouse));
        //executor.execute(new Consumer(wareHouse));




      /*  Thread waitThread = new Thread(()->{
            wareHouse.test();
        });

        Thread waitThread2 = new Thread(()->{
            wareHouse.test();
        });

        waitThread.start();
        waitThread2.start();

        Thread.sleep(2000);

        Thread notify = new Thread(()->{
            wareHouse.notify2();
        });

        notify.start();*/

//        ArrayBlockingQueue
    }
}


