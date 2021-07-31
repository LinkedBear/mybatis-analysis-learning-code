package com.linkedbear.mybatis.encapsulate.c_extra.provider;

import com.linkedbear.mybatis.encapsulate.c_extra.metadata.EntityMetadata;
import org.apache.ibatis.jdbc.SQL;

public class FindAll extends AbstractProvider {
    
    @Override
    protected String buildSql(EntityMetadata entityMetadata, Object params) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM(entityMetadata.getTablename());
        if (entityMetadata.getLoginDeleteMetadata() != null) {
            sql.WHERE(entityMetadata.getLoginDeleteMetadata().getColumnName() + " = 0");
        }
        return sql.toString();
    }
}
