package com.titans.octopus.pattern.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author vinfai
 * @since 2017/6/20
 */
public class TraceInterceptor implements MethodInterceptor {

    Object target ;

    public TraceInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("before method interceptor.");
        Object invoke = proxy.invoke(target, args);
        System.out.println("after method cglib proxy ");
        return invoke;
    }
}
