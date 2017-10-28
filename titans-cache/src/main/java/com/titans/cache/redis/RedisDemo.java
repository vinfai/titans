package com.titans.cache.redis;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
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

    @Autowired@Qualifier("redisTemplate")
    private RedisTemplate redisTemplate2;


    public void addLink(String userId, String link) {
        redisTemplate.opsForHash().put("users", userId, link);
    }

    public String getLink(String userId) {
        return (String)redisTemplate.opsForHash().get("users",userId);
    }

    /**
     * 判断1分钟内访问的次数是否超出限制10次
     * TODO 使用redis lua实现原子操作!
     * @return
     */
    public boolean checkMobileLimit(String mobile) {
        String prefix = "mobile:";
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        String key = prefix+mobile;
        Boolean hasKey = redisTemplate.hasKey(key);
        System.out.println(Thread.currentThread().getName()+" haskey:"+hasKey);
        Long count = valueOps.increment(key, 1);
        System.out.println(Thread.currentThread().getName() + ",count:" + count);
        //第一次设置过期时间.这里会有并发问题吗？
        if (count == 1) {
            redisTemplate.expire(key, 60, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName()+"expire time:"+valueOps.getOperations().getExpire(key));
        }
        if (count > 10) {
            System.out.println(Thread.currentThread().getName()+"超出限制了" + count);
            return false;
        }
        return true;
    }


    public boolean checkMobileLimit2(String mobile) {
        String prefix = "mobile:";
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        String key = prefix+mobile;
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("scripts/limit.lua")));
        script.setResultType(Long.class);
        List<String> keys = Collections.singletonList(key);
        Long result = (Long)redisTemplate2.execute(script,keys,"11");
        System.out.println(Thread.currentThread().getName() + " result:" + result + ";" + valueOps.get(key));
        return result > 0 ? true : false;
    }
}
