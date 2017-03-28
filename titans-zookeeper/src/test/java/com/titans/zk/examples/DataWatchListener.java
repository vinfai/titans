package com.titans.zk.examples;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;

import java.io.UnsupportedEncodingException;

/**
 * 监听指定的ZK数据节点本身的变化<br/>
 * NodeCache
 * @author vinfai
 * @since 2017/3/24
 */
public class DataWatchListener implements NodeCacheListener{

    /*private CuratorFramework client = null;
    {
        String connectString = "";
        //create client connection.
        client = CuratorFrameworkFactory.builder().connectString(connectString)
                .sessionTimeoutMs(20000)
                .retryPolicy(new RetryNTimes(10, 1000))
                .namespace("data")
                .build();
        client.start();

    }*/
    private CuratorFramework client;
    private String path ;//监控的路径
    private NodeCache nodeCache;
    private String preData;//更新前的数据

    /**
     *  监听具体的节点
     * @param client
     * @param path
     */
    public DataWatchListener(CuratorFramework client, String path) {
        this.client = client;
        this.path = path;
        //TODO 对象逃逸了吗
        //init(path);
        //this.nodeCache.start(true);
    }

    public void init() {
        this.nodeCache = new NodeCache(client, path);
        try {
            nodeCache.getListenable().addListener(this);
            nodeCache.start(true);
            preData = getData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void nodeChanged() throws Exception {
        String oldData = preData;
        String preData = getData();//更新后的数据
        //TODO 数据并更了，可以做一些动作，如传入Json{action：'del',id:"1"}
        System.out.println("data change now."+oldData+" --> "+ preData);

    }
    public String getData(){
        ChildData cur = nodeCache.getCurrentData();
        if (cur != null) {
            byte[] data = cur.getData();
            if (data != null) {
                try {
                    return new String(data, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public CuratorFramework getClient() {
        return client;
    }

    public void setClient(CuratorFramework client) {
        this.client = client;
    }

    public NodeCache getNodeCache() {
        return nodeCache;
    }

    public void setNodeCache(NodeCache nodeCache) {
        this.nodeCache = nodeCache;
    }
}
