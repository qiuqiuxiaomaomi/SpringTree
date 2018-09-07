package com.bonaparte.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by yangmingquan on 2018/6/30.
 */
public class JsonUtil {
    private static final Log log = LogFactory.getLog(JsonUtil.class);

    public static JSONObject merge(JSONObject json1, JSONObject json2) {
        JSONObject merged = new JSONObject();
        try {
            Set<String> keySet = Sets.newHashSet();
            if (null != json1) {
                keySet.addAll(json1.keySet());
            }
            if (null != json2) {
                keySet.addAll(json2.keySet());
            }

            keySet.stream()
                    .forEach(key->{
                        if (null != json1 && json1.containsKey(key)) {
                            merged.put(key, json1.get(key));
                        } else if (null != json2 && json2.containsKey(key)) {
                            merged.put(key, json2.get(key));
                        }
                    });
        } catch (JSONException e) {
            log.error("JsonUtil merge error, msg:" + e.getMessage(), e);
        }
        return merged;
    }

    public static JSONObject decode(String token) {
        if(token != null && !StringUtils.isEmpty(token)) {
            String[] components = token.split("\\.");
            if(components != null && components.length == 3) {
                String payloadBase64 = components[1];
                String payloadJsonStr = new String(Base64.decode(payloadBase64));
                return payloadJsonStr == null?null: JSON.parseObject(payloadJsonStr);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Map<String, Boolean> groupEquals(JSONObject json1, JSONObject json2, Map<String, List<String>> groupKeys) {
        Map<String, Boolean> result = new HashMap<String, Boolean>();
        for (String groupName : groupKeys.keySet()) {
            JSONObject groupJson1 = new JSONObject();
            JSONObject groupJson2 = new JSONObject();
            List<String> keys = groupKeys.get(groupName);
            for (String key : keys) {
                groupJson1.put(key, json1.get(key));
                groupJson2.put(key, json2.get(key));
            }
            if (groupJson1.equals(groupJson2)) {
                result.put(groupName, true);
            } else {
                result.put(groupName, false);
            }
        }
        return result;
    }
}
