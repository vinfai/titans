package com.titans.octopus.patterns.memento;

import com.titans.octopus.patterns.memento.game.Gamer;
import com.titans.octopus.patterns.memento.game.Memento;

/**
 * @author vinfai
 * @since 2017/7/14
 */
public class PlayGame {

    public static void main(String[] args) throws InterruptedException {
        Gamer gamer = new Gamer(100);
        Memento memento = gamer.createMemento();
        for (int i = 0; i < 100; i++) {
            gamer.bet();
            System.out.println(i+"当前金额:"+gamer.getMoney());
            if (gamer.getMoney() > memento.getMoney()) {
                memento = gamer.createMemento();
                System.out.println("保持游戏副本");
            } else if (gamer.getMoney() < memento.getMoney() / 2) {
                gamer.restoreMemento(memento);
                System.out.println("恢复游戏副本");
            }
        }

        Thread.sleep(10000);
        System.out.println("end");
    }
}
