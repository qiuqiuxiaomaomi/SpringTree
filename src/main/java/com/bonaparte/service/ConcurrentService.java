package com.bonaparte.service;

import com.bonaparte.entity.Charge;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Range;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by yangmingquan on 2018/7/1.
 * CyclicBarrier
 * ReentrantReadWriteLock可重入读-写锁
 * Condtion await
 */
@Service
public class ConcurrentService {
    private final static Log log = LogFactory.getLog(GuavaSeniorService.class);

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
        String abcSplit = ",1,2,3,5";
        String str = Splitter.on(",")
                .omitEmptyStrings()
                .split(abcSplit)
                .toString();
        System.out.println(str);
        String str1 = Joiner.on(",").join(1,2,3);
        String str2 = Joiner.on(",").join(intList);

        //区间
        Range range = Range.closed(1, 5).span(Range.open(3, 10));

    }
}
