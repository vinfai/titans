package com.titans.redis.pubsub;

/**
 * @author vinfai
 * @since 2017/6/13
 */
public class PointMessageDelegate  {

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
    }
}
