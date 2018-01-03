package com.titans.python.util;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author fangwenhui
 * @date 2018-01-02 17:48
 **/
public class RuntimeExec {

    public static final String ENCODE = "UTF-8";

    public static final String SPERATOR = "&&&&";


    public static String executeShell(ThreadPoolExecutor executor,String[] cmd, String encoding) {
        if (StringUtils.isBlank(encoding)) {
            encoding = ENCODE;
        }
        BufferedInputStream bis = null;
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            //为"错误输出流"单独开一个线程读取之,否则会造成标准输出流的阻塞
            executor.execute(new ErrorStreamRunnable(process.getErrorStream(),"ErrorStream"));
            //标准输出流
            bis = new BufferedInputStream(process.getInputStream());
            reader = new BufferedReader(new InputStreamReader(bis, encoding));

            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println("line:" + line);
                sb.append(line);
            }
            System.out.println("end");
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }



}

class ErrorStreamRunnable implements Runnable {

    BufferedReader bReader=null;
    String type=null;
    public ErrorStreamRunnable(InputStream is, String _type)
    {
        try{
            bReader=new BufferedReader(new InputStreamReader(new BufferedInputStream(is),"UTF-8"));
            type=_type;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        String line;
        int lineNum = 0;
        StringBuilder sb = new StringBuilder();
        try {
            while ((line = bReader.readLine()) != null) {
                lineNum++;
                sb.append(line);
                sb.append("/n");
                System.out.println(type + ">" + line);
            }
            System.out.println("error:"+sb.toString());
            bReader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
