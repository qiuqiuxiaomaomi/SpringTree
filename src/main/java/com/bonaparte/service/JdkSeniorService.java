package com.bonaparte.service;

import org.springframework.stereotype.Service;

/**
 * Created by yangmingquan on 2018/6/29.
 * jdk/jvm 的高级功能
 */
@Service
public class JdkSeniorService {

    /**
     * Class1.isAssignableFrom(Class2)
     * 判断 Class1，Class2是否相同, 判断Class1是否是Class2的父类或者接口
     * */
    public void testIsAssignedFrom(){
        System.out.println(String.class.isAssignableFrom(Object.class));
        System.out.println(Object.class.isAssignableFrom(Object.class));
        System.out.println(Object.class.isAssignableFrom(String.class));
    }
}
