package com.bonaparte.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangmingquan on 2018/7/24.
 */
public class DynaBean {
    // Bean 对象
    private Object bean;

    // Bean 类型
    private Class<?> beanClass;

    // Bean 对象的非静态属性对照表
    private Map<String, Field> beanProps;

    /**
     * 实例化一个动态 Bean
     *
     * @param beanClass
     *            Bean 类型
     */
    public DynaBean(Class<?> beanClass) {
        this.beanClass = beanClass;
        this.beanProps = getDeclaredFieldsMap(beanClass);
    }

    /**
     * 实例化一个 Bean 对象
     */
    public void newBeanInstance() {
        try {
            bean = beanClass.newInstance();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取属性的类型
     *
     * @param name
     *            属性名称
     * @return 返回属性的类型
     */
    public Class<?> getFieldType(String name) {
        Field field = beanProps.get(name);
        if (field == null) {
            throw new RuntimeException(castExceptionMessage(name));
        }
        return field.getType();
    }

    /**
     * 设置属性的值
     *
     * @param name
     *            属性名称
     * @param value
     *            属性的值
     */
    public void setFieldValue(String name, Object value) {
        Field field = beanProps.get(name);
        if (field == null) {
            throw new RuntimeException(castExceptionMessage(name));
        }
        try {
            field.set(bean, value);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取属性的值
     *
     * @param bean
     *            对象
     * @param name
     *            属性名称
     * @return 返回对象中属性的值
     */
    public Object getFieldValue(Object bean, String name) {
        Field field = beanProps.get(name);
        if (field == null) {
            throw new RuntimeException(castExceptionMessage(name));
        }
        try {
            return field.get(bean);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取 Bean 的实例
     *
     * @return 返回 Bean 的实例
     */
    public Object getBean() {
        return bean;
    }

    /**
     * 获取类声明的非静态属性表
     *
     * @param beanClass
     *            类
     * @return 返回类声明的非静态属性表
     */
    private Map<String, Field> getDeclaredFieldsMap(Class<?> beanClass) {
        // 获取类声明的属性集合
        Field[] fields = beanClass.getDeclaredFields();
        Map<String, Field> map = new HashMap<String, Field>();
        // 迭代属性集合
        for (Field field : fields) {
            // 剔除静态属性
            if ((field.getModifiers() & Modifier.STATIC) != Modifier.STATIC) {
                // 强行设置成可访问
                field.setAccessible(true);
                map.put(field.getName(), field);
            }
        }
        return map;
    }

    /**
     * 异常信息
     *
     * @param name
     *            属性名称
     * @return 返回异常信息
     */
    private String castExceptionMessage(String name) {
        return String.format("Can not found property \"%s\" in class %s", name,
                beanClass.getSimpleName());
    }
}
