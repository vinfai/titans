package com.titans.octopus.thread.lock;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * lock
 *
 * @author fangwenhui
 * @date 2018-03-09 14:59
 **/
public class Product {

    public static void main(String[] args) {
        WareHouse house = new WareHouse(5);
        Thread p1 = new Thread(new ProductWork(house), "p1");
        Thread p2 = new Thread(new ProductWork(house), "p2");

        p1.start();
        p2.start();

        Thread c1 = new Thread(new ConsumeWork(house), "c1");
        Thread c2 = new Thread(new ConsumeWork(house), "c2");

        c1.start();
        c2.start();

//        ArrayBlockingQueue

    }
}

class ProductWork implements Runnable {

    private WareHouse wareHouse;

    public ProductWork(WareHouse wareHouse) {
        this.wareHouse = wareHouse;
    }

    @Override
    public void run() {
        while (true) {
            wareHouse.put();
        }
    }
}

class ConsumeWork implements Runnable {

    private WareHouse wareHouse;

    public ConsumeWork(WareHouse wareHouse) {
        this.wareHouse = wareHouse;
    }

    @Override
    public void run() {
        while (true) {
            wareHouse.take();
        }

    }
}
