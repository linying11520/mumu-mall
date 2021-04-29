package com.mumu.gateway.common.lock;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

@Configuration
public class DistributedLockConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public RedisLockRegistry redisLockRegistry(LettuceConnectionFactory lettuceConnectionFactory) {
        String registryKey = "product:lock:registry";
        long expireAfter = 60000L;
        return new RedisLockRegistry(lettuceConnectionFactory, registryKey, expireAfter);
    }
}
