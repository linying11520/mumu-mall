package com.mumu.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@Slf4j
public class ProductModelController {
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/index")
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
