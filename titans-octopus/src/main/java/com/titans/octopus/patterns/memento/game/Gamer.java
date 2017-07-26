package com.titans.octopus.patterns.memento.game;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

/**
 * 当前游戏账号
 * @author vinfai
 * @since 2017/7/14
 */
public class Gamer {

    private Integer money;

    private List<String> fruitList = Lists.newArrayList();

    private Random random = new Random();

    private static String[] fruitsName = new String[]{"香蕉", "苹果", "葡萄", "哈密瓜"};

    public Gamer(Integer money) {
        this.money = money;
    }

    public void bet(){

        int dic = random.nextInt(6) +1;//筛子的1-6个点
        switch (dic) {
           case  1:
               money = money + 100;
               System.out.println("所持金额增加100");
               break;
            case 2: money = money/2;
                System.out.println("所持金额减半了");
                break;
            case 6:
                String f = getFruit();
                System.out.println("获得了水果" + f);
                fruitList.add(f);
                break;
            case 3:
            case 4:
            case 5:
            System.out.println("nothing");
        }
    }

    public Memento createMemento() {
        Memento memento = new Memento(this.money);
        memento.getFruitList().addAll(this.fruitList);
        return memento;
    }

    public void restoreMemento(Memento memento) {
        this.money = memento.getMoney();
        this.fruitList = memento.getFruitList();
    }

    private String getFruit() {
        String prefix = "";
        if (random.nextBoolean()) {
            prefix = "好吃的";
        }
        return prefix + fruitsName[random.nextInt(fruitsName.length)];
    }

    public Integer getMoney() {
        return money;
    }
}
