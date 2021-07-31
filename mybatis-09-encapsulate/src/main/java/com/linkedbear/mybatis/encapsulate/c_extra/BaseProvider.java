package com.linkedbear.mybatis.encapsulate.c_extra;

import com.linkedbear.mybatis.encapsulate.c_extra.annotation.Column;
import com.linkedbear.mybatis.encapsulate.c_extra.annotation.Id;
import com.linkedbear.mybatis.encapsulate.c_extra.annotation.Table;
import com.linkedbear.mybatis.encapsulate.c_extra.annotation.Transient;
import com.linkedbear.mybatis.encapsulate.c_extra.metadata.EntityMetadata;
import com.linkedbear.mybatis.encapsulate.c_extra.metadata.EntityMetadataManager;
import com.linkedbear.mybatis.encapsulate.c_extra.metadata.FieldMetadata;
import com.linkedbear.mybatis.encapsulate.util.ClassUtils;
import com.linkedbear.mybatis.encapsulate.util.StringConvertUtils;
import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.beans.Introspector;
import java.lang.reflect.Modifier;
import java.util.List;

public class BaseProvider {
    
    public String findAll(Object params, ProviderContext providerContext) {
        EntityMetadata entityMetadata = EntityMetadataManager.INSTANCE.getMetadata(providerContext.getMapperType());
        String tablename = entityMetadata.getTablename();
    
        SQL sql = new SQL();
        sql.SELECT("*").FROM(tablename);
        return sql.toString();
    }
    
    public String insert(Object params, ProviderContext providerContext) {
        EntityMetadata entityMetadata = EntityMetadataManager.INSTANCE.getMetadata(providerContext.getMapperType());
        String tablename = entityMetadata.getTablename();
        List<FieldMetadata> fieldMetadatas = entityMetadata.getFields();
        String[] columns = fieldMetadatas.stream().map(FieldMetadata::getColumnName).toArray(String[]::new);
        String[] fields = fieldMetadatas.stream().map(fm -> "#{" + fm.getFieldName() + "}").toArray(String[]::new);
    
        SQL sql = new SQL();
        sql.INSERT_INTO(tablename).INTO_COLUMNS(columns).INTO_VALUES(fields);
        return sql.toString();
    }
    
    public String update(Object params, ProviderContext providerContext) {
        Class<?> mapperClass = providerContext.getMapperType();
        Class<?> entityClass = ClassUtils.getEntityClass(mapperClass);
    
        Table table = entityClass.getAnnotation(Table.class);
        String tablename = table != null ? table.value() : resolveDefaultTablename(entityClass);
    
        final String[] idField = new String[2];
        
        SQL sql = new SQL();
        sql.UPDATE(tablename);
        
        // 解析哪些属性需要更新
        ReflectionUtils.doWithFields(entityClass, field -> {
            // 列名的处理（约定大于配置）
            String column = field.isAnnotationPresent(Column.class) && StringUtils.hasText(field.getAnnotation(Column.class).value())
                    ? field.getAnnotation(Column.class).value()
                    : StringConvertUtils.camelCaseToUnderscore(field.getName());
            // id属性记录下来，作为update依据
            if (field.isAnnotationPresent(Id.class)) {
                idField[0] = column;
                idField[1] = field.getName();
            } else {
                // 其余属性决定是否update
                field.setAccessible(true);
                if (field.get(params) != null) {
                    sql.SET(column + " = #{" + field.getName() + "}");
                }
            }
        }, field -> !field.isAnnotationPresent(Transient.class) && !Modifier.isStatic(field.getModifiers()));
    
        sql.WHERE(idField[0] + " = #{" + idField[1] + "}");
        return sql.toString();
    }
    
    private String resolveDefaultTablename(Class<?> entityClass) {
        String className = entityClass.getName();
        return StringConvertUtils.camelCaseToUnderscore(Introspector.decapitalize(className));
    }
}
