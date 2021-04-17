package com.linkedbear.mybatis.provider;

import com.linkedbear.mybatis.entity.User;
import org.apache.ibatis.jdbc.SQL;

import java.util.UUID;

public class UserMapperProvider {
    
    public String findAll() {
        SQL sql = new SQL();
        sql.SELECT("*").FROM("tbl_user");
        return sql.toString();
    }
    
    public String findAllByExample(User example) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM("tbl_user");
        if (example.getId() != null) {
            sql.AND().WHERE("id = #{id}");
        }
        if (example.getName() != null && example.getName().trim().length() > 0) {
            sql.AND().WHERE("name like concat('%', #{name}, '%')");
        }
        sql.ORDER_BY("id asc");
        return sql.toString();
    }
    
    public String save(User user) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tbl_user");
        sql.VALUES("id", "'" + UUID.randomUUID().toString().replaceAll("-", "") + "'");
        sql.VALUES("name", "#{name}");
        sql.VALUES("age", "#{age}");
        sql.VALUES("department_id", "#{department.id}");
        sql.INTO_COLUMNS("id", "name", "age", "department_id")
                .INTO_VALUES("'" + UUID.randomUUID().toString().replaceAll("-", "") + "'",
                        "#{name}", "#{age}", "#{department.id}");
        return sql.toString();
    }
    
    public String updateByExample(User user) {
        SQL sql = new SQL();
        sql.UPDATE("tbl_user");
        if (user.getName() != null && user.getName().trim().length() > 0) {
            sql.SET("name = #{name}");
        }
        if (user.getAge() != null) {
            sql.SET("age = #{age}");
        }
        sql.WHERE("id = #{id}");
        return sql.toString();
    }
    
    public String deleteById(String id) {
        SQL sql = new SQL();
        sql.DELETE_FROM("tbl_user");
        sql.WHERE("id = #{id}");
        return sql.toString();
    }
}
