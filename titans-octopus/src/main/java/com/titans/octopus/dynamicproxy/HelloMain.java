package com.titans.octopus.dynamicproxy;

import java.lang.reflect.Proxy;

/**
 * JDK 动态代理，必须有接口
 * 1.invocationHander :织入目标对象的逻辑及织入方式（前后等）.必须包含目标接口
 * 2.
 * @author vinfai
 * @since 2016/11/25
 */
public class HelloMain {

    public static void main(String[] args) {
        //JDK dynamic proxy
        HelloService service = (HelloService)Proxy.newProxyInstance(HelloMain.class.getClassLoader(),
                new Class[]{HelloService.class},new LogInvocationHandler(new HelloServiceImpl()));
        service.sayHello("vinfai");
    }
}
