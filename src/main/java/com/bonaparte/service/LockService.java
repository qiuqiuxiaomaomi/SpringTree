package com.bonaparte.service;

import redis.clients.jedis.JedisCommands;
import javax.annotation.Resource;

/**
 * Created by yangmingquan on 2018/6/29.
 * 当前采取如下两种方式实现分布式锁，后续会增加更多
 *   1: redis 实现分布式锁
 *   2：zookeeper实现分布式锁
 *
 */
public class LockService {
    @Resource(name = "jedisCluster")
    private JedisCommands jedis;

    public void redisAddLock(String key, String value) {
        if (jedis.get(key) == null) {
            jedis.setnx(key, value);
            jedis.expire(key, 60 * 30);
        }
    }

    public String redisUnLock(String key){
        if (jedis.get(key) != null){
            return jedis.del(key).toString();
        }
        return null;
    }

    /**
     * 利用Zookeeper实现分布式锁
     * */
}