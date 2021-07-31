package com.linkedbear.mybatis.encapsulate.a_provider;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

public interface BaseMapper<T> {
    
    @InsertProvider(type = BaseProvider.class, method = "insert")
    int insert(T entity);
    
    @UpdateProvider(type = BaseProvider.class, method = "update")
    int update(T entity);
    
    /*
    T get(String id);
     */
    
    @SelectProvider(type = BaseProvider.class, method = "findAll")
    List<T> findAll();
}
