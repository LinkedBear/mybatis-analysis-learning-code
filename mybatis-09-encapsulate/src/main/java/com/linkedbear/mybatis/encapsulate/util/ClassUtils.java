package com.linkedbear.mybatis.encapsulate.util;

import java.lang.reflect.ParameterizedType;

public abstract class ClassUtils {
    
    public static Class<?> getEntityClass(Class<?> mapperClass) {
        ParameterizedType type = (ParameterizedType) mapperClass.getGenericInterfaces()[0];
        return (Class<?>) type.getActualTypeArguments()[0];
    }
}
