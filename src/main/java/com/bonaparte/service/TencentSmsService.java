package com.bonaparte.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bonaparte.util.HttpUtil;
import com.bonaparte.util.TencentCloudUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

/**
 * Created by yangmingquan on 2018/8/29.
 * 腾讯云发送短信
 */
@Service
public class TencentSmsService {
    @Value("${tencentsms_url}")
    private String tencentSmsUrl;
    @Value("${tencentsms_appId}")
    private String tencentSmsAppId;
    @Value("${tencentsms_tmpId}")
    private String tencentSmsTmpId;
    @Value("${tencentsms_appkey}")
    private String tencentSmsAppKey;
    @Value("${tencentsms_sign}")
    private String tencentsmsSign;

    /**
     * 腾讯云发送短信接口
     * */
    public Object sendTencentMsg(String userNumbers, String content){
        Date date = new Date();
        JSONObject param = new JSONObject();
        JSONArray params = new JSONArray();
        params.add(content);
        param.put("params", params);
        JSONObject tel = new JSONObject();
        tel.put("mobile", userNumbers);
        tel.put("nationcode", "86");
        param.put("tel", tel);
        Long second = date.getTime() / 1000;
        param.put("time", second);
        param.put("sign", "波拿巴技术");
        param.put("tpl_id", Integer.valueOf(tencentSmsTmpId));
        Random random = new Random(1000000);
        Integer randomValue = random.nextInt();
        String codeStr = "appkey="+tencentSmsAppKey+"&random="+randomValue+"&time="+second+"&mobile="+userNumbers;
        param.put("sig", TencentCloudUtil.getSHA(codeStr));

        String wholeUrl = tencentSmsUrl + "sdkappid="+tencentSmsAppId+"&random="+randomValue;
        JSONObject json = JSON.parseObject(param.toString());
        try {
            Object object = HttpUtil.jsonPost(wholeUrl, json.toJSONString());
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("发送短信失败!");
        }
    }
}
