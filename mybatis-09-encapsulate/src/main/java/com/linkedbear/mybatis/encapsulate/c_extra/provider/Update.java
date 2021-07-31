package com.linkedbear.mybatis.encapsulate.c_extra.provider;

import com.linkedbear.mybatis.encapsulate.c_extra.metadata.EntityMetadata;
import com.linkedbear.mybatis.encapsulate.c_extra.metadata.FieldMetadata;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

public class Update extends AbstractProvider {
    
    @Override
    protected String buildSql(EntityMetadata entityMetadata, Object params) {
        FieldMetadata idMetadata = entityMetadata.getIdMetadata();
        FieldMetadata versionMetadata = entityMetadata.getVersionMetadata();
        MetaObject metaObject = SystemMetaObject.forObject(params);
        int currentVersion = (Integer) metaObject.getValue(versionMetadata.getFieldName());
        if (versionMetadata != null) {
            // 创建下一个version
            int nextVersion = currentVersion + 1;
            metaObject.setValue(versionMetadata.getFieldName(), nextVersion);
        }
    
        SQL sql = new SQL();
        sql.UPDATE(entityMetadata.getTablename());
        entityMetadata.getFields().stream().filter(fm -> !fm.isPrimary())
                // 过滤掉null的属性
                .filter(fm -> !fm.isUpdateIfNull() && metaObject.getValue(fm.getFieldName()) != null)
                .forEach(fm -> {
            sql.SET(fm.getColumnName() + " = #{" + fm.getFieldName() + "}");
        });
        sql.WHERE(idMetadata.getColumnName() + " = #{" + idMetadata.getFieldName() + "}");
        if (versionMetadata != null) {
            sql.WHERE(versionMetadata.getColumnName() + " = " + currentVersion);
        }
        return sql.toString();
    }
}
