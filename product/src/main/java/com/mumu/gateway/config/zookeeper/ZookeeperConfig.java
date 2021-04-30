package com.mumu.gateway.config.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZookeeperConfig {

    @Bean
    public CuratorFramework curatorFramework(ZookeeperProperties zookeeperProperties){
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(zookeeperProperties.getAddress())
                .sessionTimeoutMs(zookeeperProperties.getConnectionTimeoutMs())
                .retryPolicy(new ExponentialBackoffRetry(zookeeperProperties.getConnectionTimeoutMs(), zookeeperProperties.getRetryCount()))
                .build();
        curatorFramework.start();
        return curatorFramework;
    }

}
