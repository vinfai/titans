package com.titans.guava.ratelimter;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author vinfai
 * @since 2017/6/7
 */
public class RateLimiterDemo {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(10);
        while (true) {
            rateLimiter.acquire();
            System.out.println(LocalDateTime.now()+";rate:"+rateLimiter.getRate());
        }
    }
}
