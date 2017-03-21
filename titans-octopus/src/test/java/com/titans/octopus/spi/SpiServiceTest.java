package com.titans.octopus.spi;

import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * ServiceLoader.load实现原理：默认查找 "META-INF/services"+interface fullName,
 * 故资源文件名称为接口全路径
 * 文件内容：全路径接口实现类
 * @author vinfai
 * @since 2016/9/2
 */
public class SpiServiceTest {
    public static void main(String[] args) {
        ServiceLoader<SpiService> load = ServiceLoader.load(SpiService.class);
        Iterator<SpiService> iterator = load.iterator();
        while (iterator.hasNext()) {
            SpiService next =  iterator.next();
            System.out.println(next.getClass().getName());
            next.sayHello("hello");
        }
    }
}
