package com.titans.hacker.jvm;

/**
 * 对象GC 过程中GC 自我拯救!<br/>
 * 2.finalize() 方法只会调用一次!
 * @author vinfai
 * @since 2016/8/12
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes,i'm still alive.");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize mehtod executed!");
        //重新绑定到GC ROOT
        FinalizeEscapeGC.SAVE_HOOK = this;
    }
}
