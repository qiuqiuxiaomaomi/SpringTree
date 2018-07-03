package com.bonaparte.service;

import org.springframework.stereotype.Service;

import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yangmingquan on 2018/7/1.
 *  重点关注  HashMap
 *            ConcurrentHashMap
 *            TreeMap
 *            HashTable
 *   内部实现原理
 */
@Service
public class MapService {

    public void mapBasicOperate(){
        //当前HashTable 已经变更为不推荐使用， 线程安全但是性能低
        Map<String, String> hashTable = new Hashtable<>();
        hashTable.put("a", "1");
        hashTable.put("b", "2");
        hashTable.put("c", "3");
        hashTable.put("d", "5");
        hashTable.put("d", "6");
        hashTable.entrySet().forEach(x ->{
            System.out.println(x.getKey() + "" + x.getValue());
        });
        System.out.println(hashTable);

        Map<String, String> treeMap = new TreeMap<>();
        treeMap.put("a", "1");
        treeMap.put("b", "2");
        treeMap.put("c", "3");
        treeMap.put("d", "5");
        treeMap.put("d", "6");
        System.out.println(treeMap);
    }
}
