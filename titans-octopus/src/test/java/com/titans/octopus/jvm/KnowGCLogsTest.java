package com.titans.octopus.jvm;

import java.util.HashSet;

/**
 * 理解基本的GC日志,模拟实例GC，设置JVM:-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * @author vinfai
 * @since 2016/8/19
 */
public class KnowGCLogsTest {

    public static void main(String[] args) {
        KnowGCLogs knowGCLogs = new KnowGCLogs();
        knowGCLogs.testAllocation();

        HashSet set = new HashSet();
    }

    /**
     * Heap
     PSYoungGen      total 9216K, used 7786K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     eden space 8192K, 95% used [0x00000000ff600000,0x00000000ffd9a968,0x00000000ffe00000)
     from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
     to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
     ParOldGen       total 10240K, used 4096K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     object space 10240K, 40% used [0x00000000fec00000,0x00000000ff000010,0x00000000ff600000)
     Metaspace       used 3128K, capacity 4496K, committed 4864K, reserved 1056768K
     class space    used 343K, capacity 388K, committed 512K, reserved 1048576K

     Process finished with exit code 0
     */
}
