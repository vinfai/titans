package com.titans.zkclient.demo;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher;

import java.util.List;

/**
 * @author fangwenhui
 * @date 2018-03-19 17:00
 **/
public class Test {


    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = new ZkClient("10.1.11.50:2180", 10000, 20000);
        String testPath = "/home/yunxi";


        IZkStateListener stateListener = new IZkStateListener() {
            @Override
            public void handleStateChanged(Watcher.Event.KeeperState keeperState) throws Exception {
                System.out.println(keeperState+"; state changed.");
            }

            @Override
            public void handleNewSession() throws Exception {
                System.out.println("new session now.");
                System.out.println("重连，需要把");
            }
        };

        IZkChildListener childListener = new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println("path:" + s);
                System.out.println("child paths:" + list.size());
                for (String path : list) {
                    System.out.println("child path:"+path);
                }
            }
        };

        IZkDataListener dataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("path:" + dataPath + ";change data." + data);

            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println(dataPath+"; data deleted.");
            }
        };

        zkClient.subscribeStateChanges(stateListener);
        zkClient.subscribeChildChanges(testPath,childListener);
        zkClient.subscribeDataChanges(testPath,dataListener);


        //zkClient.createEphemeral(testPath, "yunxidata");

        String basePath = "/home/yunxi";

        if (!zkClient.exists(basePath)) {
            zkClient.createPersistent(basePath, true);
        }
/**/
/*

        for (int i = 0; i < 5; i++) {
            String tempPath = basePath + "/hello" + i;
            zkClient.createEphemeral(tempPath, "haha" + i);
        }
*/



        Thread.sleep(Integer.MAX_VALUE);

    }
}
