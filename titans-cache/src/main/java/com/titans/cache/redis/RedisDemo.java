package com.titans.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author vinfai
 * @since 2017/6/9
 */
@Service("redisDemo")
public class RedisDemo {

    @Autowired@Qualifier("stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    public void addLink(String userId, String link) {
        redisTemplate.opsForHash().put("users", userId, link);
    }

    public String getLink(String userId) {
        return (String)redisTemplate.opsForHash().get("users",userId);
    }

    /**
     * 判断1分钟内访问的次数是否超出限制10次
     * @return
     */
    public boolean checkMobileLimit(String mobile) {
        String prefix = "mobile:";
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        String key = prefix+mobile;
        Boolean hasKey = redisTemplate.hasKey(key);
        System.out.println("haskey:"+hasKey);
        Long count = valueOps.increment(key, 1);
        //第一次设置过期时间.这里会有并发问题吗？
        if (count == 1) {
            redisTemplate.expire(key, 60, TimeUnit.SECONDS);
            System.out.println("expire time:"+valueOps.getOperations().getExpire(key));
        }
        if (count > 10) {
            System.out.println("超出限制了" + count);
            return false;
        }
        return true;
    }
}
