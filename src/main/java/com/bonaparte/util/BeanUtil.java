package com.bonaparte.util;

import com.google.common.base.Preconditions;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangmingquan on 2018/7/1.
 */
public class BeanUtil {
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        Preconditions.checkNotNull(map);

        Object obj = beanClass.newInstance();
        Field[] fields = obj.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field ->{
            int mod = field.getModifiers();
            if(!Modifier.isStatic(mod) && !Modifier.isFinal(mod)){
                field.setAccessible(true);
                try {
                    field.set(obj, map.get(field.getName()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        return obj;
    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        Preconditions.checkNotNull(obj);
        Map<String, Object> map = new HashMap<>();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        Arrays.stream(declaredFields).forEach(field ->{
            field.setAccessible(true);
                    try {
                        map.put(field.getName(), field.get(obj));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
        );

        return map;
    }
}
