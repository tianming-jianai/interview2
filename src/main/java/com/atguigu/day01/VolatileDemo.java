package com.atguigu.day01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @BelongsProject: interview2
 * @BelongsPackage: com.atguigu.day01
 * @Author: 张世罡
 * @CreateTime: 2022/9/11 10:13
 * @Description:
 * 1. 验证volatile的可见性
 * 1.1 假设 int number = 0; number 变量之前根本没有添加volatile关键字修饰，没有可见性
 * 1.2 添加了 volatile，可以解决可见性问题
 *
 * 2. 验证 volatile不保证原子性
 * 2.1 原子性指的是什么
 *      不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割，需要整体完整要么同时成功，要么同时失败
 * 2.2 volatile 不保证原子性的案例演示
 * 2.3 why
 * 2.4 如何解决原子性
 *      * 加synchronized
 *      * 使用juc下面的AtomicInteger
 */
public class VolatileDemo {

    public static void main(String[] args) {// main是一切方法入口
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                    myData.addMyAtomic();
                }
            }, String.valueOf(i)).start();
        }
        // 需要等待上面20个线程都全部计算完成后，再用main线程取得最终的结果值看是多少？
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t int type, finally number value：" + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t AtomicInteger type, finally number value：" + myData.atomicInteger);
    }

    // volatile 可以保证可见性，及时通知其它线程，主物理内存的值已经被修改
    private static void seekOkByVolatile() {
        MyData myData = new MyData();// 资源类

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            // 暂停一会儿线程
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            myData.addNumTo60();
            System.out.println(Thread.currentThread().getName() + "\t update number value:" + myData.number);
        }, "AAA").start();

        // 第二个线程就是我们的main线程
        while (myData.number == 0) {
            // main线程一直在这样等待循环，知道number值不再等于0
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over! ");
    }
}

class MyData {
    volatile int number = 0;

    public synchronized void addNumTo60() {
        this.number = 60;
    }

    // 注意：此时number前面是加了volatile关键字修饰的，volatile不保证原子性
    public void addPlusPlus() {
        this.number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addMyAtomic() {
        atomicInteger.getAndIncrement();
    }
}
