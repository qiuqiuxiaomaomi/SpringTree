package com.bonaparte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by yangmingquan on 2018/6/29.
 */
@Service
public class SpringSeniorService {

    //@Lazy解决循环依赖的问题
    @Autowired
    private MongoSeniorService mongoSeniorService;
    @Autowired
    @Lazy
    private RedisSeniorService redisSeniorService;
}
