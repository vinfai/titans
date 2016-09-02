package com.titans.octopus.spi;

/**
 * @author vinfai
 * @since 2016/9/2
 */
public class TitanSpiServiceImpl implements SpiService {
    public void sayHello(String name) {
        System.out.println("hello,"+name+";i'm titan");
    }
}
