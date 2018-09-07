package com.bonaparte.util;

/**
 * Created by yangmingquan on 2018/9/7.
 */
public class NumberUtil {

    public static Integer parseInteger(String str, Integer def) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return def;
        }
    }

    public static Float parseFloat(String str, Float def) {
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
            return def;
        }
    }

    public static Long parseLong(String str, Long def) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            return def;
        }
    }
}
