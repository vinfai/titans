import com.google.common.collect.Lists;
import com.titans.python.util.PythonUtil;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author fangwenhui
 * @date 2018-01-03 10:18
 **/
public class PythonTest {
    private static ThreadPoolExecutor threadPoolExecutor = null;

    static {
        threadPoolExecutor = new ThreadPoolExecutor(10, 20,100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1000));
    }
    public static void main(String[] args) {

        PythonUtil.class.getClassLoader();
        try {
            File file = ResourceUtils.getFile("classpath:python/lr.py");
            String filePath = file.getAbsolutePath();
            System.out.println(filePath);

            File model = ResourceUtils.getFile("classpath:python/lr.model");
            String modelPath = model.getAbsolutePath();
            System.out.println(modelPath);

            List<String> list = Lists.newArrayList();
            list.add(filePath);
            list.add(modelPath);
            //参数
            String[] s = new String[]{"0.115680", "0.012392", "-0.043846", "0.008704", "-0.176962", "-0.486509", "0.211209", "0.186518", "-0.037117", "-0.214790", "0.063028"};
            List<String> paramList = Arrays.asList(s);
            list.addAll(paramList);

//            String param = "0.115680 0.012392 -0.043846 0.008704 -0.176962 -0.486509 0.211209 0.186518 -0.037117 -0.214790 0.063028";
//            list.add(param);
            String result = PythonUtil.execute(threadPoolExecutor,list);
            System.out.println("python result is :" + result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        threadPoolExecutor.shutdown();
    }
}
