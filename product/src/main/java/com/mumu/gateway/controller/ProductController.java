package com.mumu.gateway.controller;

import com.mumu.gateway.common.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@Controller
@Slf4j
public class ProductController {
    @Autowired
    private ExecutorService newFixThreadPool;
    @Autowired
    private RateLimiter rateLimiter;
    @Autowired
    private RedisTemplate redisTemplate;

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

    @RequestMapping("/index")
    public String index(Model model) {
        log.info("开始调用index");
        String key = "readCount";
        String today = "todayCount";
        String form = String.format("%tF", new Date());
        String todyKey = today + form;
        Integer count = 0;
        Integer todayCount = 0;
        if (redisTemplate.hasKey(key)) {
            count = (Integer) redisTemplate.opsForValue().get(key);
            log.info("总数="+count);
        } else {
            redisTemplate.opsForValue().set(key, count);
            log.info("设置总数="+count);
        }
        if (redisTemplate.hasKey(todyKey)) {
            todayCount = (Integer) redisTemplate.opsForValue().get(todyKey);
            log.info("今天数量="+count);
        } else {
            redisTemplate.opsForValue().set(todyKey, todayCount);
            log.info("设置今日数量="+count);
        }
        model.addAttribute("count", count);
        model.addAttribute("todayCount", todayCount);
        addClick();
        return "index";
    }


    @RequestMapping("/executor")
    public String executor(Model model) {
        return "executor";
    }

    @RequestMapping("/synchronized")
    public String synchronizeds(Model model) {
        return "synchronized";
    }

    @GetMapping("/addClick")
    public void addClick() {
        log.info("开始调用addClick");
        String key = "readCount";
        String today = "todayCount";
        String form = String.format("%tF", new Date());
        String todayKey = today + form;
        Integer count = 0;
        Integer todayCount = 0;
        if (redisTemplate.hasKey(key)) {
            redisTemplate.opsForValue().increment(key);
        } else {
            redisTemplate.opsForValue().set(key, count);
        }
        if (redisTemplate.hasKey(todayKey)) {
            redisTemplate.opsForValue().increment(todayKey);
        } else {
            redisTemplate.opsForValue().set(todayKey, todayCount);
        }
    }
}
