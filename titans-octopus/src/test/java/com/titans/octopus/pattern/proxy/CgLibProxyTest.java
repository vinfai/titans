package com.titans.octopus.pattern.proxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author vinfai
 * @since 2017/6/20
 */
public class CgLibProxyTest {

    public static void main(String[] args) {
       /* HelloService helloService = new HelloServiceImpl();
        TraceInterceptor interceptor = new TraceInterceptor(helloService);
        HelloService o = (HelloService)Enhancer.create(HelloService.class, interceptor);
        System.out.println(o.getClass().getName());
        o.sayHello("nihao");*/
        Hello2Service helloService = new Hello2Service();
        TraceInterceptor interceptor = new TraceInterceptor(helloService);
        Hello2Service o = (Hello2Service)Enhancer.create(Hello2Service.class, interceptor);
        System.out.println(o.getClass().getName());
        o.sayHello("nihao");

    }
}
