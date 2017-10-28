package com.titans.octopus.patterns.memento.game;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 备忘录、快照
 *
 * @author vinfai
 * @since 2017/7/14
 */
public class Memento {

    private Integer money;

    private List<String> fruitList;

    public Memento(Integer money) {
        this.money = money;
        this.fruitList = Lists.newArrayList();
    }

    void addFruit(String fruit) {
        fruitList.add(fruit);
    }

    public Integer getMoney() {
        return this.money;
    }

    List<String> getFruitList(){
        List<String> list = Lists.newArrayList();
        list.addAll(fruitList);
        return list;
    }

}
