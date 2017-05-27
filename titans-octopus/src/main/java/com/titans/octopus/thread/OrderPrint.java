package com.titans.octopus.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  用三个线程按顺序循环打印 abc 三个字母，比如 abcabcabc
 * @author vinfai
 * @since 2017/5/9
 */
public class OrderPrint {


    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        Condition condA = lock.newCondition();
        Condition condB = lock.newCondition();
        Condition condC = lock.newCondition();
        condA.signal();;

    }


}


