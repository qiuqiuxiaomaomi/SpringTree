package com.bonaparte.service;

import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by yangmingquan on 2018/6/29.
 */
@Service
public class GuavaSeniorService {
    private final static Log log = LogFactory.getLog(GuavaSeniorService.class);
    RateLimiter rateLimiter = RateLimiter.create(10.0);

    /**
     * 限流，该函数每秒最多调用10次
     * */
    public void functionRatelimit(){
        rateLimiter.acquire();
        log.info("获得令牌");
        log.info(rateLimiter.getRate());
    }

}
