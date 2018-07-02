package com.bonaparte.util;

import com.bonaparte.bean.UserInfo;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ComparisonChain;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by yangmingquan on 2018/6/29.
 * 基于guava的String的接口实现自定义String操作函数
 */
public class StringTools {
    public static String randonStr(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 将List使用分隔符构造成String
     */
    public String buildListString(List<T> stringList, String delimiter){
        return Joiner.on(delimiter).skipNulls().join(stringList);
    }

    /**
     * 将Map使用分隔符转成字符串
     * */
    public String buildMapToString(Map<String, Object> map, String delimiter){
        return Joiner.on(delimiter).withKeyValueSeparator("=").join(map);
    }

    /**
     * 将字符串转成map输出
     * @Param str: "Washington D.C=Redskins#New York City=Giants#Philadelphia=Eagles#Dallas=Cowboys";
     */
    public Splitter.MapSplitter buildStringToMap(String str, String delimiter){
        Splitter.MapSplitter mapSplitter = Splitter.on(delimiter).withKeyValueSeparator("=");
        return mapSplitter;
    }

    /**
     * 比较
     * 相比较多个if else 可以节省很多代码行
     * */
    public int compareObject(UserInfo userInfo1, UserInfo userInfo2){
        return ComparisonChain.start()
                .compare(userInfo1.getAddress(), userInfo2.getAddress())
                .compare(userInfo1.getCardNum(), userInfo2.getCardNum())
                .compare(userInfo1.getFlag(), userInfo2.getFlag())
                .compare(userInfo1.getNation(), userInfo2.getNation())
                .compare(userInfo1.getPassword(), userInfo2.getPassword())
                .compare(userInfo1.getPhone(), userInfo2.getPhone())
                .compare(userInfo2.getUserName(), userInfo2.getUserName()).result();
    }
}
