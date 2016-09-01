package com.titans.octopus.jvm;

/**
 * 理解基本的GC日志,模拟实例
 * @author vinfai
 * @since 2016/8/19
 */
public class KnowGCLogs {

    //1M 空间
    public static final int _1MB = 1024*1024;

    /**
     * 前提:-Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails
     * 通过byte[] 申请内存空间，测试GC触发及日志
     */
    public void testAllocation(){
        byte[] obj1,obj2,obj3,obj4;
        obj1 = new byte[2 * _1MB];//申请2M空间
        obj2 = new byte[2 * _1MB];
        obj3 = new byte[2 * _1MB];
        //当前eden空间8M，已用6M，剩余2M
        //再申请4M空间时，Eden不足，MinorGC
        obj4 = new byte[3 * _1MB];
    }
}
