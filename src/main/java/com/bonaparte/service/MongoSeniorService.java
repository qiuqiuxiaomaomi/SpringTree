package com.bonaparte.service;

import com.bonaparte.entity.Charge;
import com.bonaparte.util.BeanUtil;
import com.bonaparte.util.MongoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by yangmingquan on 2018/6/29.
 * mongodb 高级功能
 */
@Service
public class MongoSeniorService {
    @Autowired
    private MongoDao mongoDao;

    public static final String CHARGEINFO = "chargeinfo";

    public void testMongo(){
        Charge charge = new Charge(1,100.0);
        try {
            Map<String, Object> map = BeanUtil.objectToMap(charge);
            mongoDao.save(CHARGEINFO, map);
        }catch (Exception e){
            System.out.println("插入mongodb数据");
        }
    }
}
