package com.linkedbear.mybatis.mapper;

import com.linkedbear.mybatis.entity.Department;

import java.util.List;

public interface DepartmentDao {
    
    List<Department> findAll();
    
    Department findById(String id);
}
