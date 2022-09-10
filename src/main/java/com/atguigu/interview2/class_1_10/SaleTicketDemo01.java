package com.atguigu.interview2.class_1_10;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @BelongsProject: interview2
 * @BelongsPackage: com.atguigu.interview2.class_1_10
 * @Author: 张世罡
 * @CreateTime: 2022-03-19 22:09
 * @Description:
 */
public class SaleTicketDemo01 {
    public static void main(String[] args) {

        Ticket ticket = new Ticket();
        new Thread(()->{for(int i = 0;i<40;i++){ticket.sale();}},"t1").start();
        new Thread(()->{for(int i = 0;i<40;i++){ticket.sale();}},"t2").start();
        new Thread(()->{for(int i = 0;i<40;i++){ticket.sale();}},"t3").start();
    }
}
class Ticket {
    private int number = 30;
    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第" + (number--) + "张票，还剩下：" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
