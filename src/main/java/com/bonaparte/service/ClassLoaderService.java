package com.bonaparte.service;

import com.bonaparte.Ponaparte;
import org.springframework.stereotype.Service;

/**
 * Created by yangmingquan on 2018/7/16.
 * Java 的类加载机制
 */
@Service
public class ClassLoaderService {

    public void testClassLoader(){
        ClassLoader classLoader = ClassLoaderService.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());

        ClassLoader classLoader1 = Ponaparte.class.getClassLoader();
        System.out.println(classLoader1);
        System.out.println(classLoader1.getParent());
        System.out.println(classLoader1.getParent().getParent());
    }
}
