package com.titans.octopus.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * reflect base operation.
 * @author vinfai
 * @since 2016-09-01
 */
public class MyReflectTest {

    public static void main(String[] args) throws Exception{
//        MyReflect.class;
        //1.get class
        Class clz = MyReflect.class;
        //all fields
        Field[] declaredFields = clz.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            System.out.println(declaredField.getName()+";"+declaredField.getType());
            System.out.println("genericType:"+declaredField.getGenericType());
            System.out.println(declaredField.getModifiers());
        }
        //all constructors
        Constructor[] constructors = clz.getDeclaredConstructors();
        for (int i = 0; i < constructors.length; i++) {
            Constructor constructor = constructors[i];
            System.out.println(constructor.getParameterCount());
            System.out.println(constructor.getName());
        }
        //all methods
        Method[] methods = clz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            System.out.println(method.getName());
        }

        //create object
        //没有无参数构造，报错
        /*MyReflect instance = (MyReflect)clz.newInstance();
        instance.sayHello("hello");
        */
        Constructor c1 = clz.getConstructor(String.class);
        MyReflect ins2 = (MyReflect) c1.newInstance("vinfai");
        ins2.sayHello("hello");

        Field nameFiled = clz.getDeclaredField("name");

        //这里破坏了封装
        nameFiled.setAccessible(true);
        //private 无法访问报错！需要上面方法
        nameFiled.set(ins2, "swallow");
        ins2.sayHello("hello");


    }
}
