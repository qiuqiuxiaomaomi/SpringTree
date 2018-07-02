package com.bonaparte.service;

import com.bonaparte.dao.mapper.ChargeMapper;
import com.bonaparte.entity.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yangmingquan on 2018/6/29.
 * 事物，事物高级功能
 */
@Service
public class TransactionService {
    @Autowired
    private ChargeMapper chargeMapper;

    /**
     * 当前函数使用的注解事务
     * 该事务的回滚策略只支持运行时异常
     * */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = RuntimeException.class)
    public void saveCharge(Charge charge){
        try {
            // 第一次插入数据
            chargeMapper.insertSelective(charge);
            // 依赖于第一次数据插入结果的第二次插入
            // TODO
            saveCharge2(charge);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    /**
     * 当前函数使用的注解事务
     * 该事务的回滚策略只支持运行时异常
     * */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = RuntimeException.class)
    public void saveCharge2(Charge charge){
        try {
            // 第一次插入数据
            chargeMapper.insertSelective(charge);
            // 依赖于第一次数据插入结果的第二次插入
            // TODO
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
