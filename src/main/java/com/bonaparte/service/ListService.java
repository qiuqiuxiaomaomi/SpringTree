package com.bonaparte.service;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yangmingquan on 2018/7/1.
 * 需要重点理解Map的内部实现
 *      1: ArrayList 实现
 *      2：LinkedList 实现
 */
@Service
public class ListService {

    public void listBasicOp(){
        List<String> list1 = Lists.newArrayList();
        List<String> list2 = Lists.newArrayList();
        list1.add(null);
        list1.add(null);
        //ArrayList 查询性能好 插入性能差
        //LinkedList 查询性能差，插入性能好
    }
}
