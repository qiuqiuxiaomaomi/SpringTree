package com.bonaparte.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangmingquan on 2018/7/16.
 * lamda表达式即闭包， 相对于内部类来说延长了函数局部变量的使用周期
 * 内部类 中函数的局部变量只能被该函数访问，而lamda表达式中的变量可被外部函数访问
 */
public class ClosureService {

    public void testClosure() {
        List<String> listTemp = new ArrayList<>();
        listTemp.add("c");
        listTemp.add("c++");
        listTemp.add("java");
        listTemp.stream().forEach( x -> {
            System.out.println(x);
        });
    }
}
