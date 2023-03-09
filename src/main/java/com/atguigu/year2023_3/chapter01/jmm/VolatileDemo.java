package com.atguigu.year2023_3.chapter01.jmm;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @BelongsProject: interview2
 * @BelongsPackage: com.atguigu.year2023_3.chapter01.jmm
 * @author: 张世罡
 * @CreateTime: 2023/3/9 20:26
 * @Description:
 */
public class VolatileDemo {
    public static void main(String[] args) throws InterruptedException {
        MyData data = new MyData();

        CountDownLatch latch = new CountDownLatch(20);

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    // data.addPlus();
                    data.addAtomic();
                }
                latch.countDown();
            }, String.valueOf(i)).start();
        }
        latch.await();

        System.out.println(data.atomicInteger.get());
    }
}

class MyData {
    volatile int number = 0;

    public void addTo60() {
        number = 60;
    }

    public void addPlus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger(0);

    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }

    // 指令重排序，必须考虑指令间的数据依赖性
    public void mySort() {
        int x = 11;
        int y = 12;
        x = x + 5;
        y = x * x;
    }
}