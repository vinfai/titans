package com.titans.octopus.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author vinfai
 * @since 2017/6/20
 */
public class TraceHandler implements InvocationHandler {
    private Object target;

    public TraceHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("do before invoke obj.");
        Object obj = method.invoke(target, args);
        System.out.println(obj);
        System.out.println("after invoke method.");
        return obj;
    }
}
