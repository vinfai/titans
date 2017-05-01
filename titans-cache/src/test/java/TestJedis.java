import com.google.common.collect.Maps;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.*;
import redis.clients.util.JedisClusterCRC16;

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
            //test clear
            //Long del = jedis.del("lock:001");
            String str = jedis.get("lock:001");
            System.out.println("get lock str:"+str);
            Long status = jedis.setnx("lock:001", "lock11");
            if (status > 0) {
                System.out.println("获得锁成功");
               // jedis.del("lock:001");
            }else{
                System.out.println(status+" 获得锁失败");
            }

        }finally {
            jedis.close();
        }
    }

    @Test
    public void testPipeline() {
        Jedis jedis = pool.getResource();
        Long st = System.currentTimeMillis();
        Pipeline pipeline = jedis.pipelined();
        for (int i = 0; i < 10000; i++) {
            pipeline.set("key_" + i, "val_" + i);
        }
        pipeline.sync();
        Long et = System.currentTimeMillis();
        System.out.println("pipeline used time:" + (et - st));
        Long st2 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            jedis.set("key:" + i, "val:" + i);
        }
        Long et2 = System.currentTimeMillis();
        System.out.println("single operation used time:" + (et2 - st2));
        //相差100倍的效率.

        Long st3 = System.currentTimeMillis();
        Pipeline pipelined2 = jedis.pipelined();
        for (int i = 0; i < 10000; i++) {
            Response<String> stringResponse = pipelined2.get("key:" + i);
        }
        pipelined2.sync();
        Long et3 = System.currentTimeMillis();
        System.out.println("query by pipeline used time:" + (et3 - st3));
        Long st4 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            String s = jedis.get("key:" + i);
        }
        Long et4 = System.currentTimeMillis();
        System.out.println("query by jedis used time:" + (et4 - st4));

        jedis.close();
        /*
         * pipeline used time:124
         single operation used time:4268
         query by pipeline used time:27
         query by jedis used time:4074
         */
    }

    @Test
    public void testSetOpt() {
        Jedis jedis = pool.getResource();
        jedis.sadd("setA", "a", "b", "c", "1", "2", "3");
        jedis.sadd("setB", "a", "c", "1", "4", "5");
        Set<String> inAnotBSet = jedis.sdiff("setA", "setB");
        //预期结果: b,2,3
        for (String s : inAnotBSet) {
            System.out.println(s);
        }
        System.out.println("-------------------------");
        Set<String> inBnotASet = jedis.sdiff("setB", "setA");
        //预期结果: 4,5
        for (String s : inBnotASet) {
            System.out.println(s);
        }        jedis.close();
    }

    @After
    public void tearDown() {
        if (pool != null) {
            pool.destroy();
        }
    }

}
