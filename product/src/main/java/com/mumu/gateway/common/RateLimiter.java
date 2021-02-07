package com.mumu.gateway.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class RateLimiter {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisScript<Long> redisLimitScript;

    /**
     * 判断是否需要限流
     * @author 木木
     * @param key redis的key
     * @return boolean
     */
    public boolean isLimiter(String key) {
        //单位时间内可访问的次数
        long max = 3;
        //单位时间（10s）
        long ttl = 10000;
        long now = System.currentTimeMillis();
        long expirex = now - ttl;
        try {
            Long execute = stringRedisTemplate.execute(redisLimitScript, Collections.singletonList(key), now + "", ttl + "", expirex + "", max + "");
            if (execute != null) {
                if (execute == 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
