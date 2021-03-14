/*
package com.mumu.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {
    @Autowired
    private ExecutorService newFixThreadPool;

    private static volatile int a = 0;

    private AtomicInteger race = new AtomicInteger(0);

    */
/**
     * volatile
     *//*

    @Test
    public void addTest() {
        for (int i = 1; i <= 20; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    a++;
                }
            };
            newFixThreadPool.execute(task);
        }
        System.out.print(a);
    }

    */
/**
     * Atomic
     *//*

    @Test
    public void AtomicTest() {
        for (int i = 0; i < 2000; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    race.incrementAndGet();
                }
            };
            newFixThreadPool.execute(task);
        }
        System.out.print(race);
    }

}
*/
