package com.bonaparte.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by yangmingquan on 2018/9/7.
 */
public class PropertiesConfigUtil {

    private static Map<String, String> config = null;
    public static String[] files = new String[]{"application.properties"};

    public synchronized static void loadProperties() {
        if (config != null) {
            return;
        }
        config = new HashMap<String, String>();
        for (String file : files) {
            if (PropertiesConfigUtil.class.getClassLoader().getResource(file) == null) {
                continue;
            }
            InputStream is = null;
            try {
                is = PropertiesConfigUtil.class.getClassLoader().getResourceAsStream(file);
                Properties p = new Properties();
                p.load(is);
                for (Object key : p.keySet()) {
                    config.put(key.toString(), p.get(key).toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getString(String key) {
        loadProperties();
        return config.get(key);
    }

    public static Integer getInteger(String key, Integer def) {
        return NumberUtil.parseInteger(getString(key), def);
    }

    public static Long getLong(String key, Long def) {
        return NumberUtil.parseLong(getString(key), def);
    }
}
