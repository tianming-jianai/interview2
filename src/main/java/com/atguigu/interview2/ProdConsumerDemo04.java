package com.atguigu.interview2;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @BelongsProject: interview2
 * @BelongsPackage: com.atguigu.interview2
 * @Author: 张世罡
 * @CreateTime: 2022-03-25 15:31
 * @Description: 题目：现在两个线程，可以操作初始值为0的一个变量
 * 实现一个线程对该变量+1，一个线程对该变量-1
 * 实现交替，来10轮，变量初始值为0
 * <p>
 * 1、 高内聚低耦合前提下，线程操作资源类
 * 任何对资源类的操作，都由资源类自身操作
 * 凡多线程操作，正产情况下，都要加锁
 * <p>
 * 2、判断-干活-通知
 * 3、防止多线程虚假唤醒
 */
public class ProdConsumerDemo04 {
    public static void main(String[] args) {
        AirCondition airCondition = new AirCondition();
        // 第一期：另个线程，一个减一个加
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                    airCondition.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                    airCondition.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();
        // 第二期：四个线程，两个加两个减
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                    airCondition.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t3").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                    airCondition.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t4").start();
    }
}

class AirCondition {
    //    ReentrantLock lock = new ReentrantLock();
//    private boolean flag = false;
    private int number = 0;

    public synchronized void incr() throws InterruptedException {
        // 判断-干活-通知
//        if (number != 0) {
        while (number != 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number + "\t+");
        this.notifyAll();
    }

    public synchronized void decr() throws InterruptedException {
        // 判断-干活-通知
        while (number == 0) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number + "\t-");
        this.notifyAll();
    }
}