package com.titans.octopus.jvm.loader;

/**
 * 类加载实例
 * @author vinfai
 * @since 2016/8/16
 */
public class LoadTest01 {
    public static void main(String[] args) {
        A a = new A();//构造器中打印的this为子类对象
        a.test();

        B b = new A();
        b.test();
        int i = 331835450;
        System.out.println(i);
    }

}

class B {
    public B() {
        System.out.println(this);
    }
    public void test() {
        System.out.println("parent method exectued.");
    }
}

class A extends B{
    public A() {
//        System.out.println(this);
    }

    public void test() {
        System.out.println("subclass method test.");
    }
}
