package com.titans.octopus.temp;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * @author fangwenhui
 * @date 2018-03-22 9:54
 **/
public class RefenceValueTest {


    public static void setTest(Test3 test,Integer a) {
        test = new Test3();
        test.setValue("123");
        a = 10;
    }

    public static void main(String[] args) {
        System.out.println( Integer.MAX_VALUE/8/1024/1024);

        Integer a = 1;
        Test3 test3 = new Test3();
        setTest(test3,a);
        System.out.println(test3.getValue());
        System.out.println(a);

//        ListenableFuture listenableFuture = new ListeningExecutorService().submit()

//        Futures.addCallback();
    }

}

class Test3 {

    private String value = "abc";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

