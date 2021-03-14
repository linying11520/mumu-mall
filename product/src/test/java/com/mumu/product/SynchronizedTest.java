/*
package com.mumu.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SynchronizedTest {
    @Autowired
    private ExecutorService newFixThreadPool;

    //一百张票
    private int ticket = 100;

    // 作用于同步方法
    public synchronized void increase() {
        //模拟售票
        if (ticket == 0) {
            System.out.println("票已售罄");
        } else {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticket--;
            System.out.println("当前剩余票数" + ticket);
        }
    }

    //使用线程池执行售票业务
    @Test
    public void addTest() {
        for (int i = 1; i <= 200; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    increase();
                }
            };
            newFixThreadPool.execute(task);
        }
    }
}
*/
