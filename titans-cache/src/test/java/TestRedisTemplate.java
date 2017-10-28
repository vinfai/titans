import com.google.common.util.concurrent.RateLimiter;
import com.titans.cache.redis.RedisDemo;
import com.titans.redis.pubsub.ChangeMessageSender;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author vinfai
 * @since 2017/6/9
 */
@ContextConfiguration(locations = {"classpath*:spring-redis.xml"})
public class TestRedisTemplate extends AbstractJUnit4SpringContextTests{

    @Autowired@Qualifier("redisDemo")
    private RedisDemo redisDemo;

    @Autowired@Qualifier("changeMessageSender")
    private ChangeMessageSender changeMessageSender;
    private ExecutorService executor;

    @Before
    public void init() {
        executor = Executors.newFixedThreadPool(10);
    }
    @Test
    public void testAddLink() {
        String _link = "www.gwl.com";
        redisDemo.addLink("1", _link);
        String link = redisDemo.getLink("1");
        Assert.assertEquals(link, _link);

    }

    @Test
    public void testCheckMobileLimit() {
        String key = "13888888888";
        RateLimiter rateLimiter = RateLimiter.create(5);
        for (int i = 0; i < 20; i++) {
            rateLimiter.acquire();
            boolean b = redisDemo.checkMobileLimit(key);
            System.out.println(b);
        }

    }

    @Test
    public void testChangeMessageSender() {
        for (int i = 0; i < 5; i++) {
            changeMessageSender.sendMessage("changeTopic","hello"+i);
        }

        for (int i = 0; i < 10; i++) {
            changeMessageSender.sendMessage("point","100"+i);
        }
    }


    public ExecutorService getExecutor() {
        return executor;
    }

    public void setExecutor(ExecutorService executor) {
        this.executor = executor;
    }

    public RedisDemo getRedisDemo() {
        return redisDemo;
    }

    public void setRedisDemo(RedisDemo redisDemo) {
        this.redisDemo = redisDemo;
    }
}
