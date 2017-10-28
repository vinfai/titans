package com.titans.redis.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author vinfai
 * @since 2017/6/13
 */
public class ChangeMessageListener implements MessageListener {


    @Autowired@Qualifier("stringRedisTemplate")
    StringRedisTemplate redisTemplate;
    @Override
    public void onMessage(Message message, byte[] pattern) {
        byte[] channel = message.getChannel();
        byte[] body = message.getBody();

        String topic = (String)redisTemplate.getStringSerializer().deserialize(channel);
        String messageVal = (String) redisTemplate.getValueSerializer().deserialize(body);
        System.out.println("recive message now. "+topic + ":" + messageVal);

    }
}
