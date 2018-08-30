package com.bonaparte.config;

import org.apache.commons.lang.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangmingquan on 2018/8/30.
 */
@Configuration
public class RedissonConfig {
    @Autowired
    private RedissonProps redissonProps;

    /**
     * 单机模式
     * */
    @Bean
    @ConditionalOnProperty(name="redisson.address")
    RedissonClient redissonSingle(){
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer()
                .setAddress(redissonProps.getAddress())
                .setTimeout(redissonProps.getTimeout())
                .setConnectionPoolSize(redissonProps.getConnectionPoolSize())
                .setConnectionMinimumIdleSize(redissonProps.getConnectionMinimumIdleSize());
        if(StringUtils.isNotBlank(redissonProps.getPassword())){
            singleServerConfig.setPassword(redissonProps.getPassword());
        }
        return Redisson.create(config);
    }

    /**
     * 哨兵模式
     */
    @Bean
    @ConditionalOnProperty(name="redisson.master-name")
    RedissonClient redissonSentinel() {
        Config config = new Config();
        SentinelServersConfig serverConfig = config.useSentinelServers().addSentinelAddress(redissonProps.getSentinelAddresses())
                .setMasterName(redissonProps.getMasterName())
                .setTimeout(redissonProps.getTimeout())
                .setMasterConnectionPoolSize(redissonProps.getMasterConnectionPoolSize())
                .setSlaveConnectionPoolSize(redissonProps.getSlaveConnectionPoolSize());

        if(StringUtils.isNotBlank(redissonProps.getPassword())) {
            serverConfig.setPassword(redissonProps.getPassword());
        }
        return Redisson.create(config);
    }
}
