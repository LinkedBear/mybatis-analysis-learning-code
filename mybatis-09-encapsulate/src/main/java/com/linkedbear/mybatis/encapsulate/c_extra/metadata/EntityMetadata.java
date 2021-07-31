package com.linkedbear.mybatis.encapsulate.c_extra.metadata;

import com.linkedbear.mybatis.encapsulate.c_extra.annotation.strategy.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class EntityMetadata {
    
    private Class<?> entityClass;
    
    private String tablename;
    
    private FieldMetadata idMetadata;
    
    private FieldMetadata loginDeleteMetadata;
    
    private FieldMetadata versionMetadata;
    
    private IdGenerator idGenerator;
    
    private List<FieldMetadata> fields = new ArrayList<>();

    public EntityMetadata(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

    public Class<?> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }
    
    public FieldMetadata getIdMetadata() {
        return idMetadata;
    }
    
    public void setIdMetadata(FieldMetadata idMetadata) {
        this.idMetadata = idMetadata;
    }
    
    public FieldMetadata getLoginDeleteMetadata() {
        return loginDeleteMetadata;
    }
    
    public void setLoginDeleteMetadata(FieldMetadata loginDeleteMetadata) {
        this.loginDeleteMetadata = loginDeleteMetadata;
    }
    
    public FieldMetadata getVersionMetadata() {
        return versionMetadata;
    }
    
    public void setVersionMetadata(FieldMetadata versionMetadata) {
        this.versionMetadata = versionMetadata;
    }
    
    public IdGenerator getIdGenerator() {
        return idGenerator;
    }

    public void setIdGenerator(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public List<FieldMetadata> getFields() {
        return fields;
    }
    
    public void setFields(List<FieldMetadata> fields) {
        this.fields = fields;
    }
}
