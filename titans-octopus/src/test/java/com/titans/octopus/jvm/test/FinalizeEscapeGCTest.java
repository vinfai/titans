package com.titans.octopus.jvm.test;

import com.titans.octopus.jvm.FinalizeEscapeGC;

/**
 * 测试对象逃脱gc，结果一次GC操作都没执行！
 * @author vinfai
 * @since 2016/8/12
 */
public class FinalizeEscapeGCTest {

    public static void main(String[] args) throws Exception {
        //类静态属性引用的对象 as GC Root
        FinalizeEscapeGC.SAVE_HOOK = new FinalizeEscapeGC();
        System.out.println(FinalizeEscapeGC.SAVE_HOOK);
        //引用取消，使得对象能进行回收
        FinalizeEscapeGC.SAVE_HOOK = null;
        //没有执行啊,怎么回事！??
        System.gc();
        Thread.sleep(5000);
        //调用了对象的finalize(),但该方法中又重新建立了引用，所以没有被真正回收，
        /*if(FinalizeEscapeGC.SAVE_HOOK!=null){
            FinalizeEscapeGC.SAVE_HOOK.isAlive();
        }else{
            System.out.println("object is dead!");
        }*/
        System.out.println(FinalizeEscapeGC.SAVE_HOOK);

        //第二次调用GC
        FinalizeEscapeGC.SAVE_HOOK = null;
        System.gc();
        Thread.sleep(5000);
        if(FinalizeEscapeGC.SAVE_HOOK!=null){
            FinalizeEscapeGC.SAVE_HOOK.isAlive();
        }else{
            System.out.println("object is dead2!");
        }

    }
}
