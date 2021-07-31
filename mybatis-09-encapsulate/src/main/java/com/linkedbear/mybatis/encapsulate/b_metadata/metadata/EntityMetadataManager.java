package com.linkedbear.mybatis.encapsulate.b_metadata.metadata;

import com.linkedbear.mybatis.encapsulate.util.ClassUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public enum EntityMetadataManager {
    INSTANCE;
    
    private ConcurrentMap<Class<?>, EntityMetadata> entityMetadataMap = new ConcurrentHashMap<>();
    
    public void registerMetadata(EntityMetadata metadata) {
        this.entityMetadataMap.put(metadata.getEntityClass(), metadata);
    }
    
    public EntityMetadata getMetadata(Class<?> entityClass) {
        return entityMetadataMap.get(entityClass);
    }
    
    public EntityMetadata getMetadataByMapper(Class<?> mapperClass) {
        return getMetadata(ClassUtils.getEntityClass(mapperClass));
    }
}
