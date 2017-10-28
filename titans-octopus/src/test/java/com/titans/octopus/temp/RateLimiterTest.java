package com.titans.octopus.temp;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalDateTime;

/**
 * @author vinfai
 * @since 2017-06-07
 */
public class RateLimiterTest {

    public static void main(String[] args) {
        RateLimiter limiter = RateLimiter.create(1);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 19; i++) {

            System.out.println(limiter.acquire(1));
        }
    }
}
