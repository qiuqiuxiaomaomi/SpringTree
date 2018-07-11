package com.bonaparte.service;

import com.bonaparte.bean.Champion;
import com.bonaparte.bean.CompResult;
import com.bonaparte.bean.Competition;
import com.bonaparte.bean.UserInfo;
import com.bonaparte.dao.mapper.ChargeMapper;
import com.bonaparte.entity.Charge;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Preconditions.checkState(flag, "flag为空");
        Preconditions.checkNotNull(charge, "charge为空");
        Preconditions.checkNotNull(id, "id 为空");
        Preconditions.checkNotNull(list, "list为空");
        Preconditions.checkElementIndex(position, list.size(), "not null");
    }

    //判断是否为空，为空返回0.0，否则返回charge中的money
    public double optionalCheck(Charge charge){
        return Optional.ofNullable(charge)
                       .map(temp -> temp.getMoney())
                       .orElse(0.0);
    }

    //通常情况
    public String normalCheckMulti(Competition competition){
        if (competition != null){
            CompResult compResult = competition.getCompResult();
            if (compResult != null){
                Champion champion = compResult.getChampion();
                if (champion != null){
                    UserInfo userInfo = champion.getUserInfo();
                    if (userInfo != null){
                        return userInfo.getPhone();
                    }
                }
            }
        }
        throw new IllegalArgumentException("This value of param comp is not available");
    }

    public String OptionalCheckMulti(Competition competition){
        return Optional.of(competition)
                       .map(c ->c.getCompResult())
                       .map(r ->r.getChampion())
                       .map(u ->u.getUserInfo())
                       .map(s ->s.getPhone())
                       .orElseThrow(() -> new IllegalArgumentException("This value of param comp is not available"));
    }
}