package com.atguigu.interview2.class_1_10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @BelongsProject: interview2
 * @BelongsPackage: com.atguigu.interview2.class_1_10
 * @Author: 张世罡
 * @CreateTime: 2022-03-19 23:02
 * @Description: 大数据是语文和历史的关系：文史不分家
 */
public class NoSafeDemo {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList();
        for (int i = 0; i <30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
//        list.forEach(System.out::println);
    }
}
