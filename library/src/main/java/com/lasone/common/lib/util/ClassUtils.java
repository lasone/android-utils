package com.lasone.common.lib.util;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Date;

/**
 * Class 相关的辅助类
 *
 * @author AsOne.L
 */
public class ClassUtils {

    /**
     * 判断类是否是基础数据类型
     */
    public static boolean isBaseDataType(Class<?> clazz) {
        return clazz.isPrimitive() || clazz.equals(String.class) || clazz.equals(Boolean.class)
                || clazz.equals(Integer.class) || clazz.equals(Long.class) || clazz.equals(Float.class)
                || clazz.equals(Double.class) || clazz.equals(Byte.class) || clazz.equals(Character.class)
                || clazz.equals(Short.class) || clazz.equals(Date.class) || clazz.equals(byte[].class)
                || clazz.equals(Byte[].class);
    }

    /**
     * 根据类获取对象
     */
    public static <T> T newInstance(Class<T> clz) throws Exception {
        Constructor<?>[] cons = clz.getDeclaredConstructors();
        for (Constructor<?> c : cons) {
            Class[] cls = c.getParameterTypes();
            if (cls.length == 0) {
                c.setAccessible(true);
                return (T) c.newInstance();
            } else {
                Object[] objects = new Object[cls.length];
                for (int i = 0; i < cls.length; i++) {
                    objects[i] = getDefaultPrimitiveValue(cls[i]);
                }
                c.setAccessible(true);
                return (T) c.newInstance(objects);
            }
        }
        return null;
    }

    public static Object getDefaultPrimitiveValue(Class clz) {
        if (clz.isPrimitive()) {
            return clz == boolean.class ? false : 0;
        }
        return null;
    }

    public static boolean isCollection(Class clz) {
        return Collection.class.isAssignableFrom(clz);
    }

    public static boolean isArray(Class clz) {
        return clz.isArray();
    }

}
