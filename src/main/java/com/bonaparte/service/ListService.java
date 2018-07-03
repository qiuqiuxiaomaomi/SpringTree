package com.bonaparte.service;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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

        List<String> list3 = Lists.newLinkedList();
        list3.add("a");
        list3.add("b");
        list3.add("b");
        list3.add("c");
        list3.add("d");
        list3.add("d");
        list3.add("e");
        list3.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println(list3);
    }
}
