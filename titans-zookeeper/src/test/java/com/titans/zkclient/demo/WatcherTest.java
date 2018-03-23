package com.titans.zkclient.demo;

import org.I0Itec.zkclient.DataUpdater;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author fangwenhui
 * @date 2018-03-19 17:24
 **/
public class WatcherTest {




    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("10.1.11.50:2180", 30000, 20000);
        String basePath = "/home/yunxi";
       // zkClient.createEphemeral(basePath + "/h2", "h2");


       /* DataUpdater<String> dataUpdater = new DataUpdater<String>() {
            @Override
            public String update(String currentData) {

                return currentData + "1";
            }
        };
        zkClient.updateDataSerialized(basePath,dataUpdater);*/


        Object o = zkClient.readData(basePath);
        System.out.println(o);
      /*  String tempPath = basePath + "/hello99";
        zkClient.createEphemeral(tempPath, "haha99");*/

//        zkClient.subscribeChildChanges()
///home/yunxi/lock/l-0000000013

        String lockPath = basePath + "/lock/l-";
        //zkClient.createPersistent(lockPath+", "lock");
        //zkClient.delete(lockPath);

        ExecutorService executorService =
                Executors.newFixedThreadPool(10);
        executorService.submit(new Worker(zkClient, lockPath));
        executorService.submit(new Worker(zkClient, lockPath));
        executorService.submit(new Worker(zkClient, lockPath));
        executorService.submit(new Worker(zkClient, lockPath));
        executorService.submit(new Worker(zkClient, lockPath));


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<String> children = zkClient.getChildren(basePath + "/lock");

        for (int i = 0; i < children.size(); i++) {
            System.out.println(children.get(i));

        }





    }
}

class Worker implements Runnable {

    private ZkClient zkClient;

    private String lockPath ;

    public Worker(ZkClient zkClient, String lockPath) {
        this.zkClient = zkClient;
        this.lockPath = lockPath;
    }

    @Override
    public void run() {
        zkClient.createEphemeralSequential(lockPath, "lock");
    }
}
