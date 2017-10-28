package com.titans.octopus.jvm.loader;

/**
 * class.forName,load 区别在于class.forName 可以对静态变量、代码块初始化; load 则只是加载
 * @author vinfai
 * @since 2016/9/26
 */
public class LoadTest {
    public static void main(String[] args) {
        try {
            //initialize 是否进行初始化
            Class<?> clz = Class.forName("com.titans.octopus.jvm.loader.MyLoad",false,LoadTest.class.getClassLoader());
           // Class<?> clz2 = Class.forName("com.titans.octopus.jvm.loader.MyLoad");
            //clz.newInstance();
            Class<?> clz3 = LoadTest.class.getClassLoader().loadClass("com.titans.octopus.jvm.loader.MyLoad");
            //clz3.newInstance();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }/* catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/
    }
}
