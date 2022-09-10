package com.atguigu.interview2.class_1_10;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: interview2
 * @BelongsPackage: com.atguigu.interview2.class_1_10
 * @Author: 张世罡
 * @CreateTime: 2022-03-19 23:57
 * @Description:
 * 线程操作资源类
 * 高内聚低耦合
 *
 * 8Lock
 * 1. 标准访问，请问先打印email还是message       : email message
 * 2. 暂停4s email，请问是先访问email还是message : email message
 * 3. message -> Hello，请问先访问email还是hello : hello email
 * 4. 两部手机，请问先打印email还是message       : message email
 * 5. 两个静态同步方法，同一部手机，请问先打印邮件还是短信 : email message
 * 6. 两个静态同步方法，  两部手机，请问先打印邮件还是短信 : email message
 * 7. 1个静态同步方法，1个普通同步方法，同一部手机，请问先打印email还是message : message email
 * 8. 1个静态同步方法，1个普通同步方法，  两部手机，请问先打印email还是message : message email
 *
 */
public class Lock8Demo {
    public static void main(String[] args) throws Exception {
        Phone p = new Phone();
        Phone p2 = new Phone();
        new Thread(()->{
            try {
                p.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();

        // 故意的认为让t2在t1后执行
//        Thread.sleep(100);

        new Thread(()->{
//            p.sendMessage();
//            p.sayHello();
            p2.sendMessage();
        },"t2").start();
    }
}

class Phone{
    public static synchronized void sendEmail() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("sendEmail。。。");
    }

    public synchronized void sendMessage(){
        System.out.println("sendMessage。。。");
    }

    public void sayHello(){
        System.out.println("sayHello。。。");
    }
}
