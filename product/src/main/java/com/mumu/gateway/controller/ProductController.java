package com.mumu.gateway.controller;

import com.mumu.gateway.common.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@RestController
public class ProductController {
    @Autowired
    private ExecutorService newFixThreadPool;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RateLimiter rateLimiter;

    @Autowired
    private RedisLockRegistry redisLockRegistry;

    @Autowired
    private CuratorFramework curatorFramework;

    ReentrantLock reentrantLock = new ReentrantLock();


    @GetMapping("/testLockRegistry")
    public void testLockRegistry() {
        String key = "123456";
        Lock lock = redisLockRegistry.obtain(key);
        try {
            lock.lock();
            System.out.println("测试分布式锁");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @GetMapping("/testZookeeperLock")
    public void testZookeeperLock() {
        InterProcessLock lock = new InterProcessMutex(curatorFramework, "/locker");
        try {
            lock.acquire();
            System.out.println("测试分布式锁");
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                lock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("/testRedissonLock")
    public void testRedissonLock() {
        String key = "123456";
        Lock lock = redissonClient.getLock(key);
        try {
            lock.lock();
            System.out.println("测试分布式锁");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @GetMapping("/testLock")
    public synchronized void testLcok() {
        System.out.println(this);
        System.out.println("进入testLock");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/testLockClass")
    public void testLockClass() {
        synchronized (ProductController.class) {
            System.out.println(this);
            System.out.println(ProductController.class);
            System.out.println("进入testLockClass");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("/lock")
    public void lock() {
        System.out.println(this);
        try {
            reentrantLock.lock();
            System.out.println("进入lock");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }


    @GetMapping("/addGoods")
    public void addGoods() {
        String key = "addGoods";
        boolean limiter = rateLimiter.isLimiter(key);
        if (!limiter) {
            System.out.println(new Date() + "添加商成功");
        } else {
            System.out.println(new Date() + "添加商失败");
        }
    }


    @GetMapping("/testThread")
    public void testThread() {
        for (int i = 1; i <= 10; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    System.out.println("执行任务" + new Date());
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            newFixThreadPool.execute(task);
        }
    }


    @RequestMapping("/executor")
    public String executor(Model model) {
        return "executor";
    }

    @RequestMapping("/synchronized")
    public String synchronizeds(Model model) {
        return "synchronized";
    }

}
