package com.titans.octopus.patterns.signleton;

import java.io.Serializable;

/**
 * 静态内部类：通过同一个classLoader 保证唯一
 * 静态内部类的形式创建
 * Created by vinfai on 2016-08-27.
 */
public class Signleton implements Cloneable,Serializable{

    //1.拒绝外部创建类,反射创建
    private Signleton() {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("signleton can't clone.");
    }

    /**
     * 内部类只有调用的时候才会被初始化，故这里有延迟加载的意思。
     */
    private static class SignletonHolder{
        //2.同时通过常量属性保证唯一（同一classloader）
        static final Signleton INSTANCE = new Signleton();
    }

    static Signleton getInstance(){
        return SignletonHolder.INSTANCE;
    }

    //避免序列号、反序列化创建新的对象
    public Object readResolve() {
        return Signleton.getInstance();
    }

    public static void main(String[] args) {
        Signleton signleton = Signleton.getInstance();
        System.out.println(signleton);
        System.out.println(Signleton.getInstance());
    }
}
