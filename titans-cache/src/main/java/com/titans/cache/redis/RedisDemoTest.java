package com.titans.cache.redis;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author vinfai
 * @since 2017/6/12
 */
public class RedisDemoTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-redis.xml"});
        RedisDemo redisDemo = (RedisDemo) context.getBean("redisDemo");
        ExecutorService executor = Executors.newFixedThreadPool(10);
        String key = "haha1234";
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
//                    boolean b = redisDemo.checkMobileLimit(key);
                    boolean b = redisDemo.checkMobileLimit2(key);
                    System.out.println(b);
                }
            }
        };
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.shutdown();
    }
}
