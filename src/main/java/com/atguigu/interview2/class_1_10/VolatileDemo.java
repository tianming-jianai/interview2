package com.atguigu.interview2.class_1_10;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: interview2
 * @BelongsPackage: com.atguigu.interview2.class_1_10
 * @Author: 张世罡
 * @CreateTime: 2022-03-17 21:35
 * @Description:
 */
class MyData {
    volatile int number = 0;

    public void addT060() {
        this.number = 60;
    }
}

/*
1. 验证volatile可见性
    1.1 假如 int number = 0; number变量之前根本没有添加volatile关键字修饰
 */
public class VolatileDemo {
    public static void main(String[] args) {
        // 资源类
        MyData myData = new MyData();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addT060();
            System.out.println(Thread.currentThread().getName() + "\t" + myData.number);
        }, "t1").start();

        while (myData.number == 0) {

        }

        System.out.println(Thread.currentThread().getName() + "\t" + myData.number);
    }
}


