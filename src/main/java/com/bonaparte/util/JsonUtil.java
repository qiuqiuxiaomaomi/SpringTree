package com.bonaparte.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
}
