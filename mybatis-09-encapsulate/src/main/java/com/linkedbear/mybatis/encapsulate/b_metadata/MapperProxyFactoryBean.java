package com.linkedbear.mybatis.encapsulate.b_metadata;

import com.linkedbear.mybatis.encapsulate.b_metadata.annotation.Column;
import com.linkedbear.mybatis.encapsulate.b_metadata.annotation.Id;
import com.linkedbear.mybatis.encapsulate.b_metadata.annotation.Table;
import com.linkedbear.mybatis.encapsulate.b_metadata.annotation.Transient;
import com.linkedbear.mybatis.encapsulate.b_metadata.metadata.EntityMetadata;
import com.linkedbear.mybatis.encapsulate.b_metadata.metadata.EntityMetadataManager;
import com.linkedbear.mybatis.encapsulate.b_metadata.metadata.FieldMetadata;
import com.linkedbear.mybatis.encapsulate.util.ClassUtils;
import com.linkedbear.mybatis.encapsulate.util.StringConvertUtils;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.beans.Introspector;
import java.lang.reflect.Modifier;

public class MapperProxyFactoryBean<T> extends MapperFactoryBean<T> {
    
    public MapperProxyFactoryBean() {
    }
    
    public MapperProxyFactoryBean(Class<T> mapperInterface) {
        super(mapperInterface);
    }
    
    @Override
    protected void checkDaoConfig() {
        super.checkDaoConfig();
        Class<?> entityClass = ClassUtils.getEntityClass(this.getMapperInterface());
        EntityMetadata entityMetadata = new EntityMetadata(entityClass);
        entityMetadata.setTablename(resolveTablename(entityClass));
        
        ReflectionUtils.doWithFields(entityClass, field -> {
            // 列名的处理（约定大于配置）
            Column columnAnno = field.getAnnotation(Column.class);
            String column = columnAnno != null && StringUtils.hasText(columnAnno.value())
                    ? columnAnno.value()
                    : StringConvertUtils.camelCaseToUnderscore(field.getName());
            
            FieldMetadata fieldMetadata = new FieldMetadata(field.getName(), column);
            if (field.isAnnotationPresent(Id.class)) {
                fieldMetadata.setPrimary(true);
                entityMetadata.setIdField(field.getName());
                entityMetadata.setIdColumn(column);
                entityMetadata.setIdGenerator(BeanUtils.instantiateClass(field.getAnnotation(Id.class).generateStrategy()));
            }
            if (columnAnno != null) {
                fieldMetadata.setUpdateIfNull(columnAnno.updateIfNull());
            }
            entityMetadata.getFields().add(fieldMetadata);
        }, field -> !field.isAnnotationPresent(Transient.class) && !Modifier.isStatic(field.getModifiers()));
    
        EntityMetadataManager.INSTANCE.registerMetadata(entityMetadata);
    }
    
    private String resolveTablename(Class<?> entityClass) {
        if (entityClass.isAnnotationPresent(Table.class)) {
            return entityClass.getAnnotation(Table.class).value();
        }
        String className = entityClass.getName();
        return StringConvertUtils.camelCaseToUnderscore(Introspector.decapitalize(className));
    }
}
