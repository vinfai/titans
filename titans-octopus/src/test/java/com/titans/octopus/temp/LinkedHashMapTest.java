package com.titans.octopus.temp;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author vinfai
 * @since 2017-03-16
 */
public class LinkedHashMapTest {
    public static void main(String[] args) {
        LinkedHashMap map = new LinkedHashMap(10, 0.75F);
        map.put("a", 1);
        map.put("c", 2);
        map.put("b", 3);
       Iterator<String> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

//        ConcurrentHashMap
    }
}
