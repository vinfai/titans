package com.titans.zk.examples;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.test.TestingServer;

import java.util.List;

/**
 * zk master 选举 LeaderLatch 使用,N个client去创建同一个path，最终只有一个会成功，成功的client则为master。
 * LeaderLatch 需要手动释放
 * @author vinfai
 * @since 2017/3/28
 */
public class LeaderLatchTest {


    public static void main(String[] args) throws Exception{
        String path = "/examples/leader";
        String connectString = "192.168.248.128:2181,192.168.248.128:2182,192.168.248.128:2183";
        List<CuratorFramework> clients = Lists.newArrayList();
        List<LeaderLatch> examples = Lists.newArrayList();
//        TestingServer server = new TestingServer();


        //3个client去连接
        for (int i = 0; i < 8; i++) {
            CuratorFramework client = CuratorFrameworkFactory.builder().connectString(connectString).retryPolicy(new RetryNTimes(10, 1000)).build();
            client.start();
            clients.add(client);
            LeaderLatch example = new LeaderLatch(client, path, "client#" + i);
            example.start();
            examples.add(example);
        }

        Thread.sleep(10000);

        LeaderLatch currLeader = null;
        for (LeaderLatch example : examples) {
            if (example.hasLeadership()) {
                currLeader = example;
                break;
            }
        }
        if (currLeader != null) {
            System.out.println(currLeader.getId());
        }

        currLeader.close();//释放leader
        Thread.sleep(10000);
        System.out.println("new leader:"+examples.get(0).getLeader().getId());
        Thread.sleep(20000);

        for (CuratorFramework client : clients) {
            client.close();
        }
        for (LeaderLatch example : examples) {
            if(example!=null)
            example.close();
        }

    }
}
