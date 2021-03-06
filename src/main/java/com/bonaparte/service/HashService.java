package com.bonaparte.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yangmingquan on 2018/7/1.
 * HashMap（非线程安全）， Concurrent有很多锁（使用锁分段技术，每一段一把锁，大大的提高程序的并发性能，安全性）
 * Hash code 的计算，Hash的自增长
 */
@Service
public class HashService {
    public void hashCheck(){
        Map<String, Object> map = new HashMap();
        map.put("a", 1);
        map.putIfAbsent("b", 1);

        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("a", 1);
        concurrentHashMap.put("b", 2);

        concurrentHashMap.entrySet().forEach(x ->{
            x.setValue(x.getValue() + 10);
        });
    }

    /**
     * 模拟Hashmap的扩容过程
     * 扩容需要重新计算hash
     * 扩容与Treemap二叉树
     * 扩容与负载因子
     */
    public void HashMapResize(){

    }
}
