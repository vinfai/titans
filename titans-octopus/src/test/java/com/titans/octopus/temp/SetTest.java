package com.titans.octopus.temp;

import java.util.HashSet;
import java.util.Set;

/**
 * @author vinfai
 * @since 2017-10-24
 */
public class SetTest {
    public static void main(String[] args) {
        UserTest test01 = new UserTest("1","张三");
        UserTest test02 = new UserTest("1","张三2");
        Set<UserTest> set = new HashSet<>();
        set.add(test01);
        set.add(test02);
        System.out.println(set.size());
        //size 2,why
        //Object hashcode 方法为native 方法，返回的是对象的地址。new了2个对象，故其hashcode是不一样的。
    }
}
