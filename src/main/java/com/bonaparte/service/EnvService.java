package com.bonaparte.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by yangmingquan on 2018/6/29.
 */
@Service
public class EnvService {
    public Properties getSystemProperty(){
        //获取当前系统配置
        Properties properties = System.getProperties();
        // <> Java 编译的时候回类型推定
        Map<String, String> map = new HashMap<>();
        map = System.getenv();
        if(properties != null){
            System.out.println("---------------properties-----------");
            System.out.println(properties);
        }
        if(map != null){
            System.out.println("---------------env------------------");
            System.out.println(map);
        }
        return properties;
    }
}
