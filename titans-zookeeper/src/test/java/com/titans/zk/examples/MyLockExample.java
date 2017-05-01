package com.titans.zk.examples;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * curator 分布式锁实现
 * @author vinfai
 * @since 2017/3/29
 */
public class MyLockExample {

    public static void main(String[] args) {
        //
//        ReentrantReadWriteLock
//        ArrayBlockingQueue
        String path = "/examples/locks/biz01";
        String connectString = "192.168.248.128:2181,192.168.248.128:2182,192.168.248.128:2183";
        List<CuratorFramework> clients = Lists.newArrayList();
        //3个client去连接
        for (int i = 0; i < 3; i++) {
            CuratorFramework client = CuratorFrameworkFactory.builder().connectString(connectString).retryPolicy(new RetryNTimes(10, 1000)).build();
            client.start();
            clients.add(client);
        }
        //10个客户端同时去获得锁
        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < clients.size(); i++) {
            CuratorFramework client = clients.get(i);
            new Thread(() -> {
                InterProcessMutex mutex = new InterProcessMutex(client, path);
                //不可重入，直接等待了
//                InterProcessSemaphoreMutex mutex = new InterProcessSemaphoreMutex(client, path);
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    System.out.println("wait for start acquire lock !"+client+",times:");
                    mutex.acquire();
                    mutex.acquire();//可重入测试
                    //获取到锁后处理业务逻辑
                    System.out.println("do sth now."+client+",times:");
                    Thread.sleep(5000);
                    System.out.println("finish now."+client+",times:");
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        mutex.release();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } finally {
                    try {
                        mutex.release();
                        mutex.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        latch.countDown();
        Scanner scanner = new Scanner(System.in);//输入任何字符结束
        if (scanner.hasNext()) {
            for (CuratorFramework client : clients) {
                client.close();
            }
        }
        /*try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }
}
