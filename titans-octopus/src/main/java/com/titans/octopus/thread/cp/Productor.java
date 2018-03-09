package com.titans.octopus.thread.cp;

/**
 * productor
 *
 * @author fangwenhui
 * @date 2018-03-09 10:26
 **/
public class Productor implements Runnable {

    private WareHouse wareHouse;

    public Productor(WareHouse wareHouse) {
        this.wareHouse = wareHouse;
    }

    @Override
    public void run() {
        wareHouse.productFood();
    }
}
