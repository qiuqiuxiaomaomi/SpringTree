package com.bonaparte.service;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCommands;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by yangmingquan on 2018/6/29.
 * 当前采取如下两种方式实现分布式锁，后续会增加更多
 *   1: redis 实现分布式锁
 *   2：zookeeper实现分布式锁
 *   CAS、乐观锁与悲观锁、数据库相关锁机制、分布式锁、偏向锁、轻量级锁、重量级锁、monitor、锁优化、锁消除、锁粗化、自旋锁、可重入锁、阻塞锁、死锁
 *
 */
@Service
public class LockService implements Lock, Watcher {
    @Value("${zookeeper.servers}")
    private String zookeeperConfig;

    @Resource(name = "jedisCluster")
    private JedisCommands jedis;

    /**
     * 利用部署多实例的redis实现分布式锁
     * 开启哨兵redis-sentinel
     * */
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

    public String redisGet(String key){
        return jedis.get(key);
    }

    /**
     * 利用Zookeeper实现分布式锁
     * */
    @Override
    public void lock() {

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {

    }
}