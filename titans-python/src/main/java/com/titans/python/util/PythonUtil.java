package com.titans.python.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * python invoke
 *
 * @author fangwenhui
 * @date 2018-01-03 9:50
 **/
public class PythonUtil {


    public static String execute(ThreadPoolExecutor executor,List<String> paramList) {
        List<String> param = checkOS();
        param.add("python");
        param.addAll(paramList);
        String[] cmd = param.toArray(new String[]{});

        String s = RuntimeExec.executeShell(executor,cmd, "UTF-8");
        return s;
    }

   /* public static String executeByModel(String pyName,String pyModel) {
        List<String> param = checkOS();
        param.add("python");
        param.add(pyName);
        //参数传递..
//        pyModel
        String[] cmd = param.toArray(new String[]{});

        String s = RuntimeExec.executeShell(cmd, "UTF-8");
        return s;
    }*/

    private static  List<String> checkOS() {
        String os = System.getProperty("os.name");
        List<String> param = new ArrayList<>();
        if (StringUtils.indexOf(os, "unix") > 0) {
            param.add("/bin/sh");
            param.add("-c");
        } else {
            param.add("cmd");
            param.add("/c");
        }
        return param;
    }
}
