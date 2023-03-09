package com.atguigu.year2023_3.chapter01.jmm;

import java.util.concurrent.locks.Lock;

/**
 * @BelongsProject: interview2
 * @BelongsPackage: com.atguigu.year2023_3.chapter01.jmm
 * @author: 张世罡
 * @CreateTime: 2023/3/9 22:53
 * @Description:
 */
public class SingletonDemo {
    private static volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName()+"\t 我是构造方法SingletonDemo()");
    }

    public static SingletonDemo getInstance() {
        if (instance == null){
            synchronized (SingletonDemo.class){
                if(instance == null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                SingletonDemo.getInstance();
            }).start();
        }
    }
}
