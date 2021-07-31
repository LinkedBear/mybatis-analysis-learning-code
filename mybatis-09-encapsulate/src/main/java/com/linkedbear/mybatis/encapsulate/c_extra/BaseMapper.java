package com.linkedbear.mybatis.encapsulate.c_extra;

import com.linkedbear.mybatis.encapsulate.c_extra.provider.Delete;
import com.linkedbear.mybatis.encapsulate.c_extra.provider.FindAll;
import com.linkedbear.mybatis.encapsulate.c_extra.provider.FindByExample;
import com.linkedbear.mybatis.encapsulate.c_extra.provider.Insert;
import com.linkedbear.mybatis.encapsulate.c_extra.provider.Update;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

public interface BaseMapper<T> {
    
    @InsertProvider(type = Insert.class, method = "invoke")
    int insert(T entity);
    
    @UpdateProvider(type = Update.class, method = "invoke")
    int update(T entity);
    
    @DeleteProvider(type = Delete.class, method = "invoke")
    int delete(T entity);
    
    @SelectProvider(type = FindAll.class, method = "invoke")
    List<T> findAll();
    
    @SelectProvider(type = FindByExample.class, method = "invoke")
    List<T> findByExample(T example);
}
