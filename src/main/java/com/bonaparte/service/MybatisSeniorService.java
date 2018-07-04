package com.bonaparte.service;

import com.bonaparte.dao.mapper.ChargeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yangmingquan on 2018/6/29.
 * Mybatis 高级功能
 */
@Service
public class MybatisSeniorService {
    @Autowired
    private ChargeMapper chargeMapper;

    public void basicOp(){
        chargeMapper.getAllChargesByUid(1);
    }
}
