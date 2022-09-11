package com.atguigu.day01;

/**
 * @BelongsProject: interview2
 * @BelongsPackage: com.atguigu.day01
 * @Author: 张世罡
 * @CreateTime: 2022/9/11 10:13
 * @Description:
 * 1. 验证volatile的可见性
 * 1.1 假设 int number = 0; number 变量之前根本没有添加volatile关键字修饰，没有可见性
 */
public class VolatileDemo {
    public static void main(String[] args) {// main是一切方法入口
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

    public void addNumTo60() {
        this.number = 60;
    }
}
