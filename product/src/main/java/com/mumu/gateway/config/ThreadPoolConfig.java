package com.mumu.gateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

@Configuration
public class ThreadPoolConfig {

    @Bean("fixThreadPool")
    public ExecutorService newFixThreadPool() {
        return new ThreadPoolExecutor(200,
                200,
                20L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000),
                new ThreadPoolExecutor.AbortPolicy());
    }
}


