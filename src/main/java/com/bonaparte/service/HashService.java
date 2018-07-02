package com.bonaparte.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yangmingquan on 2018/7/1.
 * HashMap（非线程安全）， Concurrent有很多锁（使用锁分段技术，每一段一把锁，大大的提高程序的并发性能，安全性）
 */
@Service
public class HashService {
    public void hashCheck(){
        Map<String, Object> map = new HashMap();
        map.put("a", 1);
        map.putIfAbsent("b", 1);

        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("a", 1);
        concurrentHashMap.put("b", 2);
    }
}
