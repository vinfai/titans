package com.titans.octopus.concurrent.synch;

/**
 * @author vinfai
 * @since 2016-09-04
 */
public class VoliatileDemo2 extends Thread{
    //成员变量,多个线程会有线程安全问题
    private /*volatile*/ Boolean isRunning = true;
    @Override
    public void run() {
        System.out.println("thread is running");
        while (isRunning) {

        }
        System.out.println("thread is end.");
    }

    public void setRunning(Boolean running) {
        isRunning = running;
    }

    public Boolean isRunning() {
        return isRunning;
    }

    public static void main(String[] args) {
        VoliatileDemo2 thread = new VoliatileDemo2();
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.setRunning(false);
        System.out.println(thread.isRunning());

    }
}
