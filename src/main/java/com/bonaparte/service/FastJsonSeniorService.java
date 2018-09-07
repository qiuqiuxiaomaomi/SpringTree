package com.bonaparte.service;

import com.alibaba.fastjson.JSON;
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

    /**
     * @Description: 获取一个子集
     * @param json
     * @param keys
     */
    public static JSONObject sub(JSONObject json, List<String> keys) {
        JSONObject subJson = new JSONObject();
        for (String key : keys) {
            if (json.containsKey(key)) {
                subJson.put(key, json.get(key));
            }
        }

        return subJson;
    }

    /**
     * @Description: 合并两个对象产生一个JSONObject对象，重复字段用后面的覆盖前面的，第一层直接覆盖
     * @param obj1
     * @param obj2
     * @return
     * @throws
     * @author xiaolong.li@karakal.com.cn (李小龙)
     * @date 2016年4月13日 上午11:41:58
     * @version 1.0
     */
    public static JSONObject merge(Object obj1, Object obj2) {
        JSONObject json1 = new JSONObject();
        if (obj1 != null) {
            json1 = toJSONObject(obj1);
        }
        JSONObject json2 = new JSONObject();
        if (obj2 != null) {
            json2 = toJSONObject(obj2);
        }
        for (String key : json2.keySet()) {
            json1.put(key, json2.get(key));
        }
        return json1;
    }

    /**
     * @Description:  将java对象转换为JSONObject
     * @param
     * @return
     */
    public static JSONObject toJSONObject(Object javaObject) {
        return (JSONObject) JSON.toJSON(javaObject);
    }
}
