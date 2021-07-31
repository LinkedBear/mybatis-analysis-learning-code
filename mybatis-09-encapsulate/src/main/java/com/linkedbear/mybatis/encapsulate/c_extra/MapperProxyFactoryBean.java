package com.linkedbear.mybatis.encapsulate.c_extra;

import com.linkedbear.mybatis.encapsulate.c_extra.annotation.Column;
import com.linkedbear.mybatis.encapsulate.c_extra.annotation.Id;
import com.linkedbear.mybatis.encapsulate.c_extra.annotation.LogicDelete;
import com.linkedbear.mybatis.encapsulate.c_extra.annotation.Table;
import com.linkedbear.mybatis.encapsulate.c_extra.annotation.Transient;
import com.linkedbear.mybatis.encapsulate.c_extra.annotation.Version;
import com.linkedbear.mybatis.encapsulate.c_extra.annotation.query.Equals;
import com.linkedbear.mybatis.encapsulate.c_extra.annotation.query.Like;
import com.linkedbear.mybatis.encapsulate.c_extra.metadata.EntityMetadata;
import com.linkedbear.mybatis.encapsulate.c_extra.metadata.EntityMetadataManager;
import com.linkedbear.mybatis.encapsulate.c_extra.metadata.FieldMetadata;
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
                entityMetadata.setIdMetadata(fieldMetadata);
                entityMetadata.setIdGenerator(BeanUtils.instantiateClass(field.getAnnotation(Id.class).generateStrategy()));
            }
            if (columnAnno != null) {
                fieldMetadata.setUpdateIfNull(columnAnno.updateIfNull());
            }
            if (field.isAnnotationPresent(LogicDelete.class)) {
                entityMetadata.setLoginDeleteMetadata(fieldMetadata);
            } else if (field.isAnnotationPresent(Version.class)) {
                entityMetadata.setVersionMetadata(fieldMetadata);
            }
            if (field.isAnnotationPresent(Equals.class)) {
                fieldMetadata.setQuerySql(column + " = #{" + field.getName() + "}");
            } else if (field.isAnnotationPresent(Like.class)) {
                Like like = field.getAnnotation(Like.class);
                fieldMetadata.setQuerySql(column + " like concat(" + like.type().getPattern().replace("#", "#{" + field.getName() + "}") + ")");
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
