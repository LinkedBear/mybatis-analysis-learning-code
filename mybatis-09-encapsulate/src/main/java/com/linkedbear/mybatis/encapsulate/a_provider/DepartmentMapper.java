package com.linkedbear.mybatis.encapsulate.a_provider;

import com.linkedbear.mybatis.encapsulate.a_provider.entity.Department;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DepartmentMapper extends BaseMapper<Department> {
    
//    @Select("select * from tbl_department")
//    List<Department> findAll();
//
//    @Update("update tbl_department set name = #{name}, tel = #{tel} where id = #{id}")
//    int update(Department department);
//
//    @Select("select * from tbl_department where id = #{id}")
//    Department findById(String id);
}
