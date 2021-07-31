package com.linkedbear.mybatis.encapsulate.c_extra.provider;

import com.linkedbear.mybatis.encapsulate.c_extra.metadata.EntityMetadata;
import com.linkedbear.mybatis.encapsulate.c_extra.metadata.FieldMetadata;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.util.List;

public class Insert extends AbstractProvider {
    
    @Override
    protected String buildSql(EntityMetadata entityMetadata, Object params) {
        String tablename = entityMetadata.getTablename();
        List<FieldMetadata> fieldMetadatas = entityMetadata.getFields();
        String[] columns = fieldMetadatas.stream()
                .filter(fm -> !fm.getColumnName().equals(entityMetadata.getLoginDeleteMetadata().getColumnName()))
                .map(FieldMetadata::getColumnName).toArray(String[]::new);
        String[] fields = fieldMetadatas.stream()
                .filter(fm -> !fm.getColumnName().equals(entityMetadata.getLoginDeleteMetadata().getColumnName()))
                .map(fm -> "#{" + fm.getFieldName() + "}").toArray(String[]::new);
    
        SystemMetaObject.forObject(params).setValue(entityMetadata.getIdMetadata().getFieldName(), entityMetadata.getIdGenerator().next());
        
        SQL sql = new SQL();
        sql.INSERT_INTO(tablename).INTO_COLUMNS(columns).INTO_VALUES(fields);
        return sql.toString();
    }
}
