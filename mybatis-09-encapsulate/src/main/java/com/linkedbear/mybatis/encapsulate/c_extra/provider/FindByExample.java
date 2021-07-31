package com.linkedbear.mybatis.encapsulate.c_extra.provider;

import com.linkedbear.mybatis.encapsulate.c_extra.metadata.EntityMetadata;
import com.linkedbear.mybatis.encapsulate.c_extra.metadata.FieldMetadata;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

public class FindByExample extends AbstractProvider {
    
    @Override
    protected String buildSql(EntityMetadata entityMetadata, Object params) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM(entityMetadata.getTablename());
        MetaObject metaObject = SystemMetaObject.forObject(params);
        if (entityMetadata.getLoginDeleteMetadata() != null) {
            FieldMetadata loginDeleteMetadata = entityMetadata.getLoginDeleteMetadata();
            sql.WHERE(loginDeleteMetadata.getColumnName() + " = 0");
        }
        entityMetadata.getFields().stream().filter(fm -> fm.getQuerySql() != null)
                .filter(fm -> metaObject.getValue(fm.getFieldName()) != null)
                .forEach(fm -> {
            sql.WHERE(fm.getQuerySql());
        });
        return sql.toString();
    }
}
