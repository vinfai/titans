package com.titans.octopus.jvm.loader;

/**
 * @author vinfai
 * @since 2016/9/26
 */
public class MyLoad {
    private Float f = 10f;
    private static int a = 1;
    {
        System.out.println(f);
    }
    static {
        System.out.println("static block ."+a);
    }

}
