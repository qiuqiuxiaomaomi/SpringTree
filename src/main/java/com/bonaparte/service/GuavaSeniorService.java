package com.bonaparte.service;

import com.bonaparte.dao.mapper.ChargeMapper;
import com.bonaparte.entity.Charge;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yangmingquan on 2018/6/29.
 */
@Service
public class GuavaSeniorService {
    private final static Log log = LogFactory.getLog(GuavaSeniorService.class);
    RateLimiter rateLimiter = RateLimiter.create(10.0);
    @Autowired
    private ChargeMapper chargeMapper;

    /**
     * 限流，该函数每秒最多调用10次
     * */
    public void functionRatelimit(){
        rateLimiter.acquire();
        log.info("获得令牌");
        log.info(rateLimiter.getRate());
    }

    public void preconditionCheck(boolean flag,
                                  List<Integer> list,
                                  Charge charge,
                                  Integer id,
                                  Integer position){
        Preconditions.checkArgument(flag);
        Preconditions.checkNotNull(charge);
        Preconditions.checkNotNull(id);
        Preconditions.checkNotNull(list);
        Preconditions.checkElementIndex(position, list.size(), "not null");
    }
}
