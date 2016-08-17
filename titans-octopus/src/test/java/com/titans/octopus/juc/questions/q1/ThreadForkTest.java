package com.titans.octopus.juc.questions.q1;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过设置sleeptime 测试超时线程的情况，线程超时,则返回的future.get()会抛出异常，需要catch后才能得到结果集。
 * @author vinfai
 * @since 2016/8/16
 */
public class ThreadForkTest {
    public static void main(String[] args) {
        ThreadFork threadFork = new ThreadFork();
        ForkCallback c1 = new ForkCallback(4L);
        ForkCallback c2 = new ForkCallback(12L);
        ForkCallback c3 = new ForkCallback(8L);
        List<ForkCallback> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);
        list.add(c3);
        List<String> result = threadFork.forkResult(list,10L);
        for (String str : result) {
            System.out.println(str);
        }
    }
}
