package com.linkedbear.mybatis.encapsulate.b_metadata;

import com.linkedbear.mybatis.encapsulate.b_metadata.provider.FindAll;
import com.linkedbear.mybatis.encapsulate.b_metadata.provider.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

public interface BaseMapper<T> {
    
    @InsertProvider(type = Insert.class, method = "invoke")
    int insert(T entity);
    
    @UpdateProvider(type = BaseProvider.class, method = "update")
    int update(T entity);
    
    /*
    T get(String id);
     */
    
    @SelectProvider(type = FindAll.class, method = "invoke")
    List<T> findAll();
}
