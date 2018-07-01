package com.bonaparte.service;

import com.bonaparte.entity.Charge;
import com.google.common.base.CharMatcher;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by yangmingquan on 2018/7/1.
 */
public class ConcurrentService {

    public void basicConcurrent(){
        //利用Guava的工厂模式创建集合
        List<Charge> chargeList = Lists.newArrayList();
        Map<String, Object> map = Maps.newHashMap();
        Set<String> set = Sets.newHashSet();
        List<Integer> intList = Ints.asList(1,2,3,4,5);
        List<Integer> intList1 = Lists.reverse(intList);
        List<List<Integer>> listList = Lists.partition(intList, 2);

        String abc= "^J^G123天府软件园";
        //移除控制字符
        String nocontroll = CharMatcher.JAVA_ISO_CONTROL.removeFrom(abc);
        String abcDigit = CharMatcher.DIGIT.retainFrom(abc);
    }
}
