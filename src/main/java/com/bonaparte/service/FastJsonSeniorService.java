package com.bonaparte.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bonaparte.dao.mapper.ChargeMapper;
import com.bonaparte.entity.Charge;
import com.bonaparte.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yangmingquan on 2018/6/29.
 * TODO: 研究json的底层代码实现
 */
@Service
public class FastJsonSeniorService {
    @Autowired
    private ChargeMapper chargeMapper;

    public void jsonConvert(){
        List<Charge> charges = chargeMapper.selectAll();
        charges.stream()
                .forEach(x ->{
                    String strx = JSONObject.toJSONString(x);
                    System.out.println(strx);
                });
        JSONArray jsonArray = JSONArray.parseArray(charges.toString());

        JSONObject jsonObject1 = jsonArray.getJSONObject(0);
        JSONObject jsonObject2 = jsonArray.getJSONObject(1);
        JSONObject mergeJson = JsonUtil.merge(jsonObject1, jsonObject2);
    }
}
