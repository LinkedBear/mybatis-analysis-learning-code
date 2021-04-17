package com.linkedbear.mybatis.mapper;

import com.linkedbear.mybatis.entity.User;
import com.linkedbear.mybatis.provider.UserMapperProvider;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

public interface UserAnnotationMapper {
    
    @SelectProvider(type = UserMapperProvider.class, method = "findAll")
    List<User> findAll();
    
    @SelectProvider(type = UserMapperProvider.class, method = "findAllByExample")
    List<User> findAllByExample(User example);
    
    @InsertProvider(type = UserMapperProvider.class, method = "save")
    void save(User user);
    
    @UpdateProvider(type = UserMapperProvider.class, method = "updateByExample")
    int updateByExample(User user);
    
    @DeleteProvider(type = UserMapperProvider.class, method = "deleteById")
    int deleteById(String id);
}
