package com.titans.guava.collections;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Iterator;
import java.util.Set;

/**
 * multisets 可重复的元素集合。like arrayList & map.
 * 所有Multiset实现的内存消耗随着不重复元素的个数线性增长。
 *
 * @author vinfai
 * @since 2016/9/20
 */
public class MultisetsExample {

    public static void main(String[] args) {
        Multiset<String> multiset = HashMultiset.create(10);
        multiset.add("1");
        multiset.add("2");
        multiset.add("2");
        multiset.add("3");
        multiset.add("4");
        multiset.add("4");
        multiset.add("4");

        boolean b0 = multiset.remove("2");
        System.out.println(b0);
        int count = multiset.remove("4",1);//同时删除多个相同元素,occurences 频次
        //the count of the element before the operation; possibly zero
        System.out.println("delete count:"+count);
        //querys
        Iterator<String> iterator = multiset.iterator();
        while (iterator.hasNext()) {
            String v = iterator.next();
            System.out.println(v+" -->元素在set中的个数:"+multiset.count(v));
        }



        //无重复元素的
        Set<String> set = multiset.elementSet();
        for (String s : set) {
            System.out.println("元素："+s);
        }

        //直接增加元素了,牛逼
        multiset.setCount("2", 3);
        //删除所有元素
        multiset.setCount("1",0);
        Iterator<String> iterator1 = multiset.iterator();
        while (iterator1.hasNext()) {
            String v = iterator1.next();
            System.out.println(v+" -->元素在set中的个数:"+multiset.count(v));
        }

        System.out.println("集合的大小:"+multiset.size());
        System.out.println("集合不重复元素的大小:"+multiset.elementSet().size());


    }
}
