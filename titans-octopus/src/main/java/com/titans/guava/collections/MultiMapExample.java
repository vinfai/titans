package com.titans.guava.collections;

import com.google.common.base.Optional;
import com.google.common.collect.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 搞个map这么简单
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

        HashMultimap<String, Integer> setMultimap = HashMultimap.create();
        setMultimap.put("1", 1);
        setMultimap.put("1", 2);
        setMultimap.put("1", 3);
        setMultimap.put("1", 3);
        Map<String, Collection<Integer>> stringCollectionMap2 = setMultimap.asMap();
        for (String s : stringCollectionMap2.keySet()) {
            Collection<Integer> integers = stringCollectionMap2.get(s);
            for (Integer i : integers) {
                System.out.println(s+" --> "+i);
            }
        }

        //找出2个Map的不同之处与相同之处，以Map形式返回
        ImmutableMap<String,Integer> oneMap=ImmutableMap.of("key1",1,"key2",2);
        ImmutableMap<String,Integer> twoMap=ImmutableMap.of("key11",11,"key2",2);
        MapDifference<String,Integer> diffHadle=Maps.difference(oneMap,twoMap);
        Map<String, Integer> stringIntegerMap = diffHadle.entriesInCommon();
        System.out.println(stringIntegerMap);
        Map<String, Integer> stringIntegerMap1 = diffHadle.entriesOnlyOnRight();
        System.out.println(stringIntegerMap1);

        String str = (String) Optional.fromNullable(null).or("hello");
        System.out.println(str);


        Set<String> set1 = ImmutableSet.of("2222","ukey","recordid","演出","剧院","版本","订单号","项目","场馆","场次","物品","消费时间","下单时间","退款时间","结算基价","折扣率","票面价格");
        Set<String> set12 = ImmutableSet.of("用户","场次合计","ukey","recordid","订单号","项目","场馆","演出","剧院","版本","订单号","项目","场馆","场次","物品","消费时间","下单时间","退款时间","结算基价","折扣率","票面价格");
        Sets.SetView<String> difference = Sets.difference(set1, set12);
        System.out.println(difference);
        Sets.SetView<String> intersection = Sets.intersection(set1, set12);
        System.out.println(intersection);


    }
}
