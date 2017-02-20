package com.titans.octopus.dynamicproxy;

/**
 * 被代理对象
 * @author vinfai
 * @since 2016/11/25
 */
public class HelloServiceImpl implements HelloService {


    @Override
    public String sayHello(String name) {
        return "hello world."+name;
    }
}
