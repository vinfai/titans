package com.titans.redis.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author vinfai
 * @since 2017/6/13
 */
@Service("changeMessageSender")
public class ChangeMessageSender {

    @Autowired
    @Qualifier("stringRedisTemplate")
    StringRedisTemplate redisTemplate;

    public void sendMessage(String channel, Serializable message) {
        redisTemplate.convertAndSend(channel, message);
    }
}
