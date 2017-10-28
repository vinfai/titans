package com.titans.octopus.pattern.proxy;

/**
 * @author vinfai
 * @since 2017/6/20
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        System.out.println("hello,"+name);
    }
}
