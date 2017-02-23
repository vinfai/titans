import com.google.common.collect.Maps;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author vinfai
 * @since 2017/1/24
 */
public class TestJedis {

   /* public static void main(String[] args) {
        JedisPool pool = new JedisPool(new JedisPoolConfig(),"192.168.248.128");
        Jedis jedis = pool.getResource();
        jedis.set("mystr", "helloworld");
        String mystr = jedis.get("mystr");
        System.out.println(mystr);

        pool.destroy();

    }*/
    private JedisPool pool = null;
    @Before
    public void setUp(){
        pool = new JedisPool(new JedisPoolConfig(),"192.168.248.128");
    }
    @Test
    public void testString() {
        Jedis jedis = pool.getResource();
        jedis.set("mystr", "item1");
        Assert.assertEquals(jedis.get("mystr"),"item1");
        //数字类型的增减操作
        jedis.set("counter", "1");
        jedis.incrBy("counter",10);
        Assert.assertEquals(jedis.get("counter"),"11");

        jedis.decrBy("counter",2);
        Assert.assertEquals(jedis.get("counter"), "9");
        jedis.del("mystr","counter");
        Assert.assertEquals(jedis.get("mystr"), null);
        jedis.close();
    }

    /**
     * hash test
     */
    @Test
    public void testHash() {
        Jedis jedis = pool.getResource();
        try{
            jedis.hset("user:1002", "name", "vinfai");
            HashMap<String, String> map = Maps.newHashMap();
            //hmset 整个map，key,value 使用string或者序列化成byte[]
            map.put("name", "jack");
            map.put("age", "55");
            map.put("weight", "101");
            jedis.hmset("user:1001", map);

            Map<String, String> userMap = jedis.hgetAll("user:1001");
            Assert.assertEquals(userMap.get("name"), "jack");
            Assert.assertEquals(jedis.hget("user:1002", "name"), "vinfai");

            //删除多个属性
            jedis.hdel("user:1001","name");
            Assert.assertEquals(jedis.hget("user:1001", "name"),null);

            //返回map对象中的所有key
            Set<String> hkeys = jedis.hkeys("user:1001");
            //返回map对象中的所有value
            List<String> hvals = jedis.hvals("user:1001");
            for(String  key : hkeys){
                System.out.println(key);
            }
        }finally {
            jedis.close();
        }
    }


    @After
    public void tearDown() {
        if (pool != null) {
            pool.destroy();
        }
    }

}
