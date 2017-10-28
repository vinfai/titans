import com.titans.dao.BaseShardingDao;
import com.titans.model.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * locations = {"classpath*:spring-redis.xml"}
 * @author vinfai
 * @since 2017/7/27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/appcontext-hibernate.xml","classpath*:spring/appcontext-sharding.xml"})
public class ShardingDaoTest {

    @Autowired@Qualifier("shardingDao")
    private BaseShardingDao dao;
    @Test
    public void testSave() {
        for (int i = 0; i < 1000; i++) {
            Order order = new Order();
            order.setUserId(1000+i);
            order.setStatus("Y");
            dao.save(order);
        }
    }

    @Test
    public void testGetObjectById() {
        Order o = dao.getObjectById(Order.class,97366261343715328L);
        Assert.assertEquals(o.getUserId(), 1024);
    }

    @Test
    public void testDeleteById() {
        dao.deleteObjectById(Order.class, 97366261536653312L);
    }
}
