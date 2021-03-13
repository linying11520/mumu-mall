package com.mumu.gateway.controller;

import com.mumu.gateway.common.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.ExecutorService;

@Controller
public class ProductController {
    @Autowired
    private ExecutorService newFixThreadPool;
    @Autowired
    private RateLimiter rateLimiter;


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
        return "index";
    }

    @RequestMapping("/executor")
    public String executor(Model model) {
        return "executor";
    }
}
