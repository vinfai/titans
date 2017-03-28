package com.titans.zk.examples;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * 创建Client链接
 * @author vinfai
 * @since 2017/3/23
 */
public class CreateClientExample {

    public static void main(String[] args) {
        //指数递增方式重试,第一次1s,2:2s
        try {
            //创建方式1：
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
           /*

            CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.248.128:2181,192.168.248.128:2182", retryPolicy);
            client.start();//启动
            client.create().forPath("/zktest", "zktestdata".getBytes("UTF-8"));
            */
            CuratorFramework client2 = CuratorFrameworkFactory.builder()
                    .connectString("192.168.248.128:2181,192.168.248.128:2182,192.168.248.128:2183")
                    .namespace("myzkdata")//名称空间，根目录,业务隔离
                    .retryPolicy(retryPolicy)
                    .sessionTimeoutMs(2000).build();
            CuratorListener listener = new CuratorListener() {
                @Override
                public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
                    if (event.getType().equals(CuratorEventType.GET_DATA)) {
                        System.out.println(event.getPath()+"; get data event.");
                    }else if (event.getType().equals(CuratorEventType.CREATE)) {
                        System.out.println(event.getPath()+"; CREATE !! event.");
                    }
                    System.out.println("event:"+event.getName()+","+event.getPath()+";"+event.getPath());
                }
            };
            client2.getCuratorListenable().addListener(listener);
            client2.start();

            //
//  client2.create().forPath("/c2data", "data001".getBytes("utf-8"));
            //临时节点,client 关闭以后就没有了。注意观察
            client2.create().withMode(CreateMode.EPHEMERAL).forPath("/tempdata", "124".getBytes());
            client2.create().withMode(CreateMode.EPHEMERAL).forPath("/c2data2", "haha".getBytes());

            //
            byte[] bytes = client2.getData().inBackground().forPath("/c2data2");

            client2.delete().guaranteed().forPath("/c2data");



            Thread.sleep(10000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

    }
}
