package com.bonaparte.service;

import com.bonaparte.dao.mapper.GroovyMapper;
import com.bonaparte.entity.Groovy;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yangmingquan on 2018/7/16.
 * groovy 脚本
 * 不仅可以执行groovy代码块，也可以执行groovy文件
 */
@Service
public class GroovyService {
    @Autowired
    private GroovyMapper groovyMapper;

    public void excuteGroovyFromMysql(){
        Binding binding = new Binding();
        binding.setVariable("money", 6.99);
        GroovyShell gs = new GroovyShell(binding);
        Groovy groovy = groovyMapper.selectByPrimaryKey(1);
        Object result = gs.evaluate(groovy.getGroovyScript());
        System.out.println(result.toString());
    }
}
