package com.titans.octopus.thread.cp;

/**
 * consumer
 *
 * @author fangwenhui
 * @date 2018-03-09 10:27
 **/
public class Consumer implements Runnable {
    private WareHouse wareHouse;

    public Consumer(WareHouse wareHouse) {
        this.wareHouse = wareHouse;
    }

    @Override
    public void run() {
        wareHouse.consumerFood();
    }
}
