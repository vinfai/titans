package com.titans.octopus.thread.lock;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * repository
 *
 * @author fangwenhui
 * @date 2018-03-09 15:03
 **/
public class WareHouse {

    private ReentrantLock lock;

    private Condition conditionPro;

    private Condition conditionCons;

    private Integer capacity;

    private List<String> list = Lists.newArrayList();


    public WareHouse(Integer capacity) {
        this.capacity = capacity;
        this.lock = new ReentrantLock();
        this.conditionPro = lock.newCondition();
        this.conditionCons = lock.newCondition();
    }

    public void put() {
        lock.lock();
        try {
            while (list.size() == capacity) {
                conditionPro.await();
            }
            String obj = new Random().nextInt(100)+"";
            list.add(obj);
            System.out.println("put obj:"+obj);
            conditionCons.signal();

        } catch (Exception e) {
            conditionCons.signal();
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


    public void take() {
        lock.lock();
        try {
            while (list.size() == 0) {
                conditionCons.await();
            }
            String t = list.remove(0);
            System.out.println("get object"+t);
            conditionPro.signal();
        } catch (Exception e) {
            conditionPro.signal();
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
