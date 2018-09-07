package com.bonaparte.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangmingquan on 2018/9/7.
 */
public class ClassUtils {
    /**
     * @Description: 获取类的所有字段(包括父类)
     * @param clazz
     */
    public static List<Field> getInheritFields(Class<?> clazz) {
        Class<?> superClazz = clazz;
        List<Field> fields = new ArrayList<Field>();
        while(superClazz != null) {
            Field[] fs = superClazz.getDeclaredFields();
            for (Field f : fs) {
                fields.add(f);
            }
            superClazz = superClazz.getSuperclass();
        }
        return fields;
    }

    /**
     * @Description: 通过反射设置字段的值
     * @param fieldName
     * @param obj
     * @param value
     */
    public static void setFieldValue(String fieldName, Object obj, Object value) throws NoSuchFieldException, SecurityException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        boolean isAccessible =  field.isAccessible();
        field.setAccessible(true);
        try {
            field.set(obj, value);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (IllegalAccessException e) {
            throw e;
        } finally {
            field.setAccessible(isAccessible);
        }
    }

    /**
     * @Description: 通过反射获取字段的值
     * @param fieldName
     * @param obj
     */
    public static Object getFieldValue(String fieldName, Object obj) throws NoSuchFieldException, SecurityException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        boolean isAccessible =  field.isAccessible();
        field.setAccessible(true);
        try {
            return field.get(obj);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (IllegalAccessException e) {
            throw e;
        } finally {
            field.setAccessible(isAccessible);
        }
    }

    @SuppressWarnings("rawtypes")
    private static final Class[] primitiveClass = new Class[]{Byte.class, Short.class, Integer.class, Long.class, BigInteger.class, Float.class, Double.class, BigDecimal.class, String.class};
    /**
     * @Description: 判断是否为原始基本数据类型
     * @param clazz
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean isPrimitiveClass(Class<?> clazz) {
        for (Class c : primitiveClass) {
            if (c.equals(clazz)) {
                return true;
            }
        }
        return false;
    }
}
