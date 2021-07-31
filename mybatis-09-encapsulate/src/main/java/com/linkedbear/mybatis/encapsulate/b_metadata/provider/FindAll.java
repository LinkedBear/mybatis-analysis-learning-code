package com.linkedbear.mybatis.encapsulate.b_metadata.provider;

import com.linkedbear.mybatis.encapsulate.b_metadata.metadata.EntityMetadata;
import org.apache.ibatis.jdbc.SQL;

public class FindAll extends AbstractProvider {
    
    @Override
    protected String buildSql(EntityMetadata entityMetadata, Object params) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM(entityMetadata.getTablename());
        return sql.toString();
    }
}
