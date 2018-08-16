package com.bonaparte.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by yangmingquan on 2018/8/16.
 * 阿拉伯数字转换成汉字
 */
public class PinyinUtil {
    private static Map<String,String> numberMap = new LinkedHashMap<String, String>();
    static {
        String nums[] = new String[]{"零","一","二","三","四","五","六","七","八","九"};
        for(int i=0;i<nums.length;i++){
            numberMap.put(String.valueOf(i), nums[i]);
        }
    }

    private static String replaceNum(String str){
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder("");
        for(char c : chars){
            String sc = String.valueOf(c);
            if(numberMap.containsKey(sc)){
                sb.append(numberMap.get(sc));
            }else{
                sb.append(sc);
            }
        }
        return sb.toString();
    }
}
