package com.titans.zk.examples;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * @author vinfai
 * @since 2017/3/24
 */
public class DataWatchListenerTest {
    static String encoding = "UTF-8";
    public static void main(String[] args) throws Exception {

        String connectString = "192.168.248.128:2181,192.168.248.128:2182,192.168.248.128:2183";
        //create client connection.
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(connectString)
                .sessionTimeoutMs(20000)
                .retryPolicy(new RetryNTimes(10, 1000))
                .namespace("data")
                .build();
        client.start();
        String path = "/smsconfig";//eg：短信配置方式

//        Stat stat = client.checkExists().forPath(path);
//        if (stat == null) {
           client.create().withMode(CreateMode.EPHEMERAL).forPath(path,"voice".getBytes(encoding));//建立临时节点测试，下次连接时自动删除
            /* NodeCache nodeCache = new NodeCache(client, path);
            nodeCache.start(true);
            nodeCache.getListenable().addListener(new NodeCacheListener() {
                String predata = new String(nodeCache.getCurrentData().getData(),encoding);

                @Override
                public void nodeChanged() throws Exception {
                    String temp = predata;
                    String newdata = new String(nodeCache.getCurrentData().getData(),encoding);
                    predata = newdata;
                    System.out.println("datachange "+temp+" --> "+ newdata);
                }
            });*/
            //节点上增加了监听

            DataWatchListener dataWatchListener = new DataWatchListener(client, path);
            dataWatchListener.init();//初始化+监听

//        }
        //节点变化了
        client.setData().forPath(path, "v3".getBytes(encoding));

        byte[] bytes = client.getData().forPath(path);
        if (bytes != null) {
            System.out.println(new String(bytes, "utf-8"));
        }
        client.setData().forPath(path, "v4".getBytes(encoding));

        client.setData().forPath(path, "v5".getBytes(encoding));
        client.setData().forPath(path, "v6".getBytes(encoding));
        client.setData().forPath(path, "v7".getBytes(encoding));
        client.setData().forPath(path, "v8".getBytes(encoding));
        client.setData().forPath(path, "v9".getBytes(encoding));
        Thread.sleep(10000);

    }
}
