package com.titans.octopus.reflect;

/**
 * @author vinfai
 * @since 2016-09-01
 */
public class MyReflect {

    public static void main(String[] args) {
       // MyReflect.class.getGenericInterfaces();

    }

    private String name;

    public MyReflect(String name) {
        this.name = name;
    }

    public String sayHello(String msg){
        String msg1 = "hello," + name + ":"+msg;
        System.out.println(msg1);
        return msg1;
    }

    private void changeName(String name) {
        this.name = name;
    }
}
