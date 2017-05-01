package com.titans.octopus.juc.questions.q1.threadlocal;

import java.util.Map;

/**
 * 当前线程附加信息，静态属性和方法
 *
 * @author vinfai
 * @since 2017/4/18
 */
public class ThreadAttachmentUtil {

    /**
     * 通过ThreadLocal实现数据线程的隔离
     */
    private static ThreadLocal<Map<String, String>> holder = new ThreadLocal<>();

    public static void clear() {
        holder.remove();
    }

    public static void setThreadAttachment(Map<String, String> data) {
        holder.set(data);
    }

    public static Map<String, String> getThreadAttachment() {
        return holder.get();
    }



}
