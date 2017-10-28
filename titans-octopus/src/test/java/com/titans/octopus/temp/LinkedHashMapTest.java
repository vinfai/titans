package com.titans.octopus.temp;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author vinfai
 * @since 2017-03-16
 */
public class LinkedHashMapTest {
    public static void main(String[] args) {
        LinkedHashMap map = new LinkedHashMap(10, 0.75F,true);
        map.put("a", 1);
        map.put("c", 2);
        map.put("b", 3);
        map.put("c",12);//accessOrder = true,表示按照访问顺序对key排序
        Iterator<String> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

//        ThreadPoolExecutor
//1.core
//        ConcurrentHashMap
    }
}
