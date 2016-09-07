package com.titans.tools.jvm;

/**
 * 查看线程状态，及如何查看等工具，如
 * jps -l 查看jvm进程
 * jstack <pid> 查看java进程内的线程堆栈信息
 * http://my.oschina.net/feichexia/blog/196575
 *
 * @author vinfai
 * @since 2016/9/7
 */
public class ThreadStateAnalyze {

    /**
     * j
     * //blockedTask-thread-2 获得了锁对象类，进入了线程，
     "blockedTask-thread-2" #14 prio=5 os_prio=0 tid=0x0000000058989800 nid=0x293c waiting on condition [0x0000000059e3f000]
     java.lang.Thread.State: TIMED_WAITING (sleeping)//因为调用了sleep方法
     at java.lang.Thread.sleep(Native Method)
     at com.titans.tools.jvm.ThreadStateAnalyze$BlockedTask.run(ThreadStateAnalyze.java:63)
     - locked <0x00000000d5d1d140> (a java.lang.Class for com.titans.tools.jvm.ThreadStateAnalyze$BlockedTask)
     at java.lang.Thread.run(Thread.java:745)
     //blockedTask-thread-1 阻塞等待锁（锁被lockedTask-thread-2获取了一直没释放）
     "blockedTask-thread-1" #13 prio=5 os_prio=0 tid=0x0000000058987000 nid=0x1180 waiting for monitor entry [0x0000000059a8f000]
     java.lang.Thread.State: BLOCKED (on object monitor)
     at com.titans.tools.jvm.ThreadStateAnalyze$BlockedTask.run(ThreadStateAnalyze.java:63)
     - waiting to lock <0x00000000d5d1d140> (a java.lang.Class for com.titans.tools.jvm.ThreadStateAnalyze$BlockedTask)
     at java.lang.Thread.run(Thread.java:745)
     //timeWaiting-thread" 调用了sleep(1000),故一直处于等待
     "timeWaiting-thread" #12 prio=5 os_prio=0 tid=0x0000000058973000 nid=0x2c10 in Object.wait() [0x0000000059d3f000]
     java.lang.Thread.State: TIMED_WAITING (on object monitor)
     at java.lang.Object.wait(Native Method)
     - waiting on <0x00000000d5d1a2f8> (a java.lang.Class for com.titans.tools.jvm.ThreadStateAnalyze$TimeWaitingTask)
     at com.titans.tools.jvm.ThreadStateAnalyze$TimeWaitingTask.run(ThreadStateAnalyze.java:46)
     - locked <0x00000000d5d1a2f8> (a java.lang.Class for com.titans.tools.jvm.ThreadStateAnalyze$TimeWaitingTask)
     at java.lang.Thread.run(Thread.java:745)
     // WaitingThread线程在WaitingTask实例上等待
     "waiting-thread" #11 prio=5 os_prio=0 tid=0x0000000058971800 nid=0x2c0c in Object.wait() [0x0000000059c2f000]
     java.lang.Thread.State: WAITING (on object monitor)
     at java.lang.Object.wait(Native Method)
     - waiting on <0x00000000d5d16ca0> (a java.lang.Class for com.titans.tools.jvm.ThreadStateAnalyze$WaitingTask)
     at java.lang.Object.wait(Object.java:502)
     at com.titans.tools.jvm.ThreadStateAnalyze$WaitingTask.run(ThreadStateAnalyze.java:27)
     - locked <0x00000000d5d16ca0> (a java.lang.Class for com.titans.tools.jvm.ThreadStateAnalyze$WaitingTask)
     at java.lang.Thread.run(Thread.java:745)
     * @param args
     */
    public static void main(String[] args) {
        new Thread(new WaitingTask(),"waiting-thread").start();
        new Thread(new TimeWaitingTask(),"timeWaiting-thread").start();
        new Thread(new BlockedTask(),"blockedTask-thread-1").start();
        new Thread(new BlockedTask(),"blockedTask-thread-2").start();
    }

    //线程等待 wait
    static class WaitingTask implements Runnable{

        @Override
        public void run() {
            while (true){
                synchronized (WaitingTask.class){
                    try {
                        System.out.println("thread is wait....");
                        WaitingTask.class.wait();//必须和锁一起用哦！！！
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //线程超时等待 wait(100)
    static class TimeWaitingTask implements Runnable{

        @Override
        public void run() {
           while (true){
              /*synchronized (TimeWaitingTask.class){
                  try {
                      System.out.println("timed waiting.....");
                      //线程一直等待超时,超时后继续进入runnable?
                      TimeWaitingTask.class.wait(1000);
                      System.out.println("等待超时了，继续");
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }*/
              //这两种方式都会是线程state=Timed_WAITING
               try {
                   System.out.println("sleeped. timed waiting.....");
                   //线程一直等待超时,超时后继续进入runnable?
                   Thread.sleep(1000);
                   System.out.println("等待超时了，继续");
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        }
    }

    //线程竞争阻塞(多个线程同时要进同步块，肯定一个成功，一个阻塞)
    static class BlockedTask implements Runnable{
        @Override
        public void run() {
            synchronized (BlockedTask.class){//类对象锁
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
