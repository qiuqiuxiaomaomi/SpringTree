package com.bonaparte.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by yangmingquan on 2018/9/7.
 */
public class URLUtil {

    private static final Log log = LogFactory.getLog(URLUtil.class);

    public static String encode(String s, String enc) {
        try {
            return URLEncoder.encode(s, enc);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    public static String encode(String s) {
        return encode(s, "UTF-8");
    }

    public static String decode(String s, String enc) {
        try {
            return URLDecoder.decode(s, enc);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
