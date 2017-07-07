import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vinfai
 * @since 2017/7/7
 */
public class LogTest {

    public static void main(String[] args) throws InterruptedException {
        Logger logger = LoggerFactory.getLogger(LogTest.class);
        logger.debug("你好，{},对吧,{}",new Object[]{"sb","sb2"});
        logger.info(" info hello ,{}","jack22");
        logger.warn(" warn hellO ,{}","lily11");
        Thread.sleep(10000);
    }
}
