package com.linkedbear.mybatis.mapper;

import com.linkedbear.mybatis.entity.User;

import java.util.List;

public interface UserMapper {

    List<User> findAll();

    List<User> findAllLazy();

    List<User> findAllUseTypeHandler();
    
    int saveUser(User user);
    
    List<User> findAllByDepartmentId(String departmentId);
}
