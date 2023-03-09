package com.atguigu.year2023_3.chapter01.jmm;

/**
 * @BelongsProject: interview2
 * @BelongsPackage: com.atguigu.year2023_3.chapter01.jmm
 * @author: 张世罡
 * @CreateTime: 2023/3/9 22:04
 * @Description:
 */
public class ReSortSeqDemo {
    int a = 0;
    boolean flag = false;

    public void method01() {
        a = 1; // 语句1
        flag = true; // 语句2
    }
    // 多线程环境中线程交替执行，由于编译器优化重排的存在
    // 两个线程中使用的变量能否保证一致性是无法确定的，结果无法预测

    public void method02() {
        if (flag) {
            a = a + 5;
            System.out.println("**** return value: " + a);
        }
    }

    public static void main(String[] args) {
        ReSortSeqDemo demo = new ReSortSeqDemo();
        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                demo.method01();
                demo.method02();
            }).start();
        }
    }
}
