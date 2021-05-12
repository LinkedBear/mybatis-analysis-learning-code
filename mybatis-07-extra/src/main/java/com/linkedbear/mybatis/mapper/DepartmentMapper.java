package com.linkedbear.mybatis.mapper;

import com.linkedbear.mybatis.entity.Department;

import java.util.List;

public interface DepartmentMapper {
    
    List<Department> findAll();
    
    int update(Department department);
    
    Department findById(String id);
    
    int cleanCache();
}
