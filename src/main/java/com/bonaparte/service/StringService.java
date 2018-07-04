package com.bonaparte.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * Created by yangmingquan on 2018/7/1.
 */
@Service
public class StringService {

    public void testString1(){
        String str="   \r\n12345gsggheugu\rsehisgehhbbb~@#%&*()   sehhu    ";
        StringUtils.substringAfter(str, "g");
        StringUtils.substringAfterLast(str, "g");
        StringUtils.substringBefore(str, "g");
        StringUtils.substringBetween("g", "u");
        StringUtils.trim(str);
        String a ="";
        String b =null;
        StringUtils.trimToNull(b);
        StringUtils.trimToEmpty(a);
        StringUtils.containsIgnoreCase("abcdefgeee", "a");
        StringUtils.containsNone("aeeeggestt", "xyz");
    }
}
