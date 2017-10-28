package com.titans.octopus.pattern.proxy;

import java.lang.reflect.Proxy;

/**
 * 动态代理
 * @author vinfai
 * @since 2017/6/20
 */
public class DynamicProxyTest {

    public static void main(String[] args) {
      /*  Integer i = 10;
        TraceHandler handler = new TraceHandler(i);
        Object proxy = Proxy.newProxyInstance(DynamicProxyTest.class.getClassLoader(), new Class[]{Comparable.class}, handler);
        proxy.equals(10);//调用comparable方法*/

//        Long.valueOf()
        HelloService helloService = new HelloServiceImpl();
        TraceHandler handler = new TraceHandler(helloService);
        HelloService proxyService = (HelloService)Proxy.newProxyInstance(DynamicProxyTest.class.getClassLoader(), new Class[]{HelloService.class}, handler);
        proxyService.sayHello("haha");
    }
}
