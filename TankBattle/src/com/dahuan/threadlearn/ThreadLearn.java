package com.dahuan.threadlearn;

/**
 * 线程第一阶段学习（基础内容）
 */
public class ThreadLearn {
    public static void main(String[] args) {
        System.out.println("线程学习");
        Runtime runtime = Runtime.getRuntime();
        System.out.println(runtime.availableProcessors());
        MyThread myThread = new MyThread();
        try {
            myThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MyThread.yield();
        myThread.setDaemon(true);//设置守护线程
    }
}

class MyThread extends Thread {
    int count = 0;
    @Override
    public void run() {
        super.run();
        while (count < 10) {
            System.out.println("你好王文欢" + (++count));
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
