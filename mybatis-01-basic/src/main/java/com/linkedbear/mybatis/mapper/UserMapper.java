package com.linkedbear.mybatis.mapper;

import com.linkedbear.mybatis.entity.User;

import java.util.List;

public interface UserMapper {

    List<User> findAll();

    List<User> findAllLazy();
}
