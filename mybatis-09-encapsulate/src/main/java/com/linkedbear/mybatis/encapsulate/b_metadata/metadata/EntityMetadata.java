package com.linkedbear.mybatis.encapsulate.b_metadata.metadata;

import com.linkedbear.mybatis.encapsulate.b_metadata.annotation.strategy.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class EntityMetadata {
    
    private Class<?> entityClass;
    
    private String tablename;
    
    private String idField;
    
    private String idColumn;
    
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
    
    public String getIdField() {
        return idField;
    }
    
    public void setIdField(String idField) {
        this.idField = idField;
    }
    
    public String getIdColumn() {
        return idColumn;
    }
    
    public void setIdColumn(String idColumn) {
        this.idColumn = idColumn;
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
