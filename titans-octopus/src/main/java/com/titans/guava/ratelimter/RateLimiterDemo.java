package com.titans.guava.ratelimter;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalDateTime;

/**
 * @author vinfai
 * @since 2017/6/7
 */
public class RateLimiterDemo {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(0.5);
        while (true) {

            System.out.println(LocalDateTime.now()+";rate:"+rateLimiter.getRate()+";"+ rateLimiter.acquire());
        }
    }
}
