package com.atguigu.year2023_3.chapter01.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @BelongsProject: interview2
 * @BelongsPackage: com.atguigu.year2023_3.chapter01.jmm
 * @author: 张世罡
 * @CreateTime: 2023/3/9 23:37
 * @Description: CAS
 * compareAndSet 比较并交换
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        atomicInteger.compareAndSet(0,1);

        System.out.println(atomicInteger.get());

        int i = atomicInteger.incrementAndGet();
        System.out.println(i);
    }
}
