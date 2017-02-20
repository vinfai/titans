package com.titans.octopus.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * AOP里面这里包含了织入的逻辑、joinpoint<br/>
 * 1.需要有目标对象
 * 2.
 * @author vinfai
 * @since 2016/11/25
 */
public class LogInvocationHandler implements InvocationHandler{

    private Object target;

    public LogInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       //前置织入
        System.out.println("before origin method invoked."+args);
        Object obj = method.invoke(target,args);
        System.out.println(obj);
        System.out.println("after mehtod invoked");
        return obj;
    }
}
