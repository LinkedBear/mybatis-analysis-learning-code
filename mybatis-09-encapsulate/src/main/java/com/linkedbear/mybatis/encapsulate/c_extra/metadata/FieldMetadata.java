package com.linkedbear.mybatis.encapsulate.c_extra.metadata;

public class FieldMetadata {
    
    private String fieldName;
    
    private String columnName;
    
    private boolean primary = false;
    
    private boolean updateIfNull = false;
    
    private String querySql;
    
    public FieldMetadata(String fieldName, String columnName) {
        this.fieldName = fieldName;
        this.columnName = columnName;
    }
    
    public String getFieldName() {
        return fieldName;
    }
    
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    
    public String getColumnName() {
        return columnName;
    }
    
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    
    public boolean isPrimary() {
        return primary;
    }
    
    public void setPrimary(boolean primary) {
        this.primary = primary;
    }
    
    public boolean isUpdateIfNull() {
        return updateIfNull;
    }
    
    public void setUpdateIfNull(boolean updateIfNull) {
        this.updateIfNull = updateIfNull;
    }
    
    public String getQuerySql() {
        return querySql;
    }
    
    public void setQuerySql(String querySql) {
        this.querySql = querySql;
    }
}
