package com.linkedbear.mybatis.mapper;

import com.linkedbear.mybatis.entity.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DepartmentAnnotationMapper {
    
    @Select("select * from tbl_department")
    @ResultMap("departmentUseResultsId")
    List<Department> findAll();
    
    @Select("select * from tbl_department where id = #{id}")
    Department findById(String id);
    
    @Select("<script>select * from tbl_department "
                    + "<where>"
                    + "<if test='id != null'>and id = #{id} </if>"
                    + "<if test='name != null'>and name like concat('%', #{name}, '%') </if>"
                    + "<if test='tel != null'>and tel = #{id} </if>"
                    + "</where>"
                    + "</script>")
    List<Department> findAllByExample(Department example);
    
    @Select("select * from tbl_department")
    @Results(id = "departmentUseResultsId", value = {
        @Result(id = true, property = "id", column = "id"),
        @Result(property = "name", column = "tel"),
        @Result(property = "tel", column = "name")
    })
    List<Department> findAllByResults();
    
    @Select("select * from tbl_department")
    @Results(id = "departmentWithResults", value = {
        @Result(id = true, property = "id", column = "id"),
        @Result(property = "name", column = "tel"),
        @Result(property = "tel", column = "name"),
        @Result(property = "users", column = "id", many = @Many(select = "com.linkedbear.mybatis.mapper.UserMapper.findAllByDepartmentId"))
    })
    List<Department> findAllByResultsWithUsers();
    
    @Insert("insert into tbl_department (id, name, tel) values (#{id}, #{name}, #{tel})")
    int save(Department department);
    
    @Insert("insert into tbl_dept2 (name, tel) values (#{name}, #{tel})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int saveUseGeneratedKeys(Department department);
    
    @Update("update tbl_department set name = #{name} where id = #{id}")
    int updateById(Department department);
    
    @Delete("delete from tbl_department where id = #{id}")
    int deleteById(String id);
}
