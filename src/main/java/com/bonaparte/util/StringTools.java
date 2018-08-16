package com.bonaparte.util;

import com.bonaparte.bean.UserInfo;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ComparisonChain;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.util.HtmlUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yangmingquan on 2018/6/29.
 * 基于guava的String的接口实现自定义String操作函数
 */
public class StringTools {
    public static String randonStr(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 将List使用分隔符构造成String
     */
    public String buildListString(List<T> stringList, String delimiter){
        return Joiner.on(delimiter).skipNulls().join(stringList);
    }

    /**
     * 将Map使用分隔符转成字符串
     * */
    public String buildMapToString(Map<String, Object> map, String delimiter){
        return Joiner.on(delimiter).withKeyValueSeparator("=").join(map);
    }

    /**
     * 将字符串转成map输出
     * @Param str: "Washington D.C=Redskins#New York City=Giants#Philadelphia=Eagles#Dallas=Cowboys";
     */
    public Splitter.MapSplitter buildStringToMap(String str, String delimiter){
        Splitter.MapSplitter mapSplitter = Splitter.on(delimiter).withKeyValueSeparator("=");
        return mapSplitter;
    }

    /**
     * 比较
     * 相比较多个if else 可以节省很多代码行
     * */
    public int compareObject(UserInfo userInfo1, UserInfo userInfo2){
        return ComparisonChain.start()
                .compare(userInfo1.getAddress(), userInfo2.getAddress())
                .compare(userInfo1.getCardNum(), userInfo2.getCardNum())
                .compare(userInfo1.getFlag(), userInfo2.getFlag())
                .compare(userInfo1.getNation(), userInfo2.getNation())
                .compare(userInfo1.getPassword(), userInfo2.getPassword())
                .compare(userInfo1.getPhone(), userInfo2.getPhone())
                .compare(userInfo2.getUserName(), userInfo2.getUserName()).result();
    }

    public static boolean checkSpecialChar(String name, String extra) {
        if (name == null) {
            return false;
        }

        String regEx = "[^\\p{L}\\p{Nd}" + extra + "]";
        Pattern p     = Pattern.compile(regEx);
        Matcher m     = p.matcher(name.toLowerCase());

        return m.find();
    }

    /**
     * 字符解码
     * @param str   原始字符串
     * @return
     */
    public static String stringDecode(String str) {
        if (str == null) {
            return null;
        }

        str = org.springframework.util.StringUtils.trimWhitespace(str);
        str = HtmlUtils.htmlUnescape(str);
        return str;
    }

    public static String toSearchUnicode(String str) {
        String result = "";

        str = str.toLowerCase();

        for (int i = 0; i < str.length(); i++) {
            char sChar = str.charAt(i);

            if (checkSpecialChar(String.valueOf(sChar), "")) {
                result += String.valueOf(sChar);
            } else {
                int    chr1     = (char) sChar;
                String hexStr   = Integer.toHexString(chr1);
                String startStr = "";

                for (int j = 0; j < (4 - hexStr.length()); j++) {
                    startStr += "0";
                }

                result += "u" + startStr + hexStr;
            }
        }

        return result;
    }

    /**
     * 删除所有特殊字符
     * @param name   原始字符串
     * @param extra   指定不删除字符串
     * @return
     */
    public static String trimSpecialChar(String name, String extra) {
        if (name == null) {
            return null;
        }

        String regEx = "[^\\p{L}\\p{Nd}" + extra + "]";
        Pattern p     = Pattern.compile(regEx);
        Matcher m     = p.matcher(name.toLowerCase());
        String re    = m.replaceAll("").trim();

        if (re.equals("")) {
            return name;
        }

        return re;
    }

    public static String spliceUrl(String url, Map<String, String> params){
        if(params == null){
            return url;
        }
        url += "?";
        Iterator iter = params.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry<String, String> entry = (Map.Entry)iter.next();
            String value = entry.getValue();
            url += entry.getKey() + "=" + value + "&";
        }
        url = url.substring(0, url.length() - 1);
        System.out.println("远程请求地址为"+url);
        return url;
    }
}
