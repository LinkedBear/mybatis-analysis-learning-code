package com.linkedbear.mybatis.encapsulate.c_extra.provider;

import com.linkedbear.mybatis.encapsulate.c_extra.metadata.EntityMetadata;
import com.linkedbear.mybatis.encapsulate.c_extra.metadata.EntityMetadataManager;
import org.apache.ibatis.builder.annotation.ProviderContext;

public abstract class AbstractProvider {
    
    public String invoke(Object params, ProviderContext context) {
        EntityMetadata entityMetadata = EntityMetadataManager.INSTANCE.getMetadataByMapper(context.getMapperType());
        return buildSql(entityMetadata, params);
    }
    
    protected abstract String buildSql(EntityMetadata entityMetadata, Object params);
}
