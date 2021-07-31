package com.linkedbear.mybatis.encapsulate.c_extra.provider;

import com.linkedbear.mybatis.encapsulate.c_extra.metadata.EntityMetadata;
import com.linkedbear.mybatis.encapsulate.c_extra.metadata.FieldMetadata;
import org.apache.ibatis.jdbc.SQL;

public class Delete extends AbstractProvider {
    
    @Override
    protected String buildSql(EntityMetadata entityMetadata, Object params) {
        SQL sql = new SQL();
        FieldMetadata loginDeleteMetadata = entityMetadata.getLoginDeleteMetadata();
        FieldMetadata idMetadata = entityMetadata.getIdMetadata();
        if (loginDeleteMetadata != null) {
            sql.UPDATE(entityMetadata.getTablename());
            sql.SET(loginDeleteMetadata.getColumnName() + " = 1");
            sql.WHERE(idMetadata.getColumnName() + " = #{" + idMetadata.getFieldName() + "}");
        } else {
            sql.DELETE_FROM(entityMetadata.getTablename());
            sql.WHERE(idMetadata.getColumnName() + " = #{" + idMetadata.getFieldName() + "}");
        }
        return sql.toString();
    }
}
