package com.titans.guava.collections;

import com.google.common.collect.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author vinfai
 * @since 2016/9/20
 */
public class MultiMapExample {
    public static void main(String[] args) {
//        Maps.newHashMap();
//        Sets.newHashSet();
        //LinkedHashMultimap<String,String> linkedHashMultimap = LinkedHashMultimap.create();
        //linkedHashMultimap.put("hello","h");
        ArrayListMultimap<String,Integer> multiMap = ArrayListMultimap.create();
        multiMap.put("1", 1);
        multiMap.put("1", 2);
        multiMap.put("1", 3);
        multiMap.put("1", 1);
        multiMap.put("b", 1);
        multiMap.put("b", 2);
        multiMap.put("b", 5);
        Map<String, Collection<Integer>> stringCollectionMap = multiMap.asMap();
        for (String s : stringCollectionMap.keySet()) {
            Collection<Integer> integers = stringCollectionMap.get(s);
            for (Integer i : integers) {
                System.out.println(s+" --> "+i);
            }
        }
        multiMap.remove("b", 2);
        List<Integer> bList =  multiMap.get("b");
        System.out.println(bList);



    }
}
