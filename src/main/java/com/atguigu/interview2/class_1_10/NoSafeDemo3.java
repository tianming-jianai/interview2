package com.atguigu.interview2.class_1_10;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @BelongsProject: interview2
 * @BelongsPackage: com.atguigu.interview2.class_1_10
 * @Author: 张世罡
 * @CreateTime: 2022-03-19 23:51
 * @Description:
 */
public class NoSafeDemo3 {
    public static void main(String[] args) {
//        Map<String,String> map = new HashMap<>();
        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();

        }
    }
}
