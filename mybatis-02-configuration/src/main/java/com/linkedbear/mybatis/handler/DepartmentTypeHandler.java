package com.linkedbear.mybatis.handler;

import com.linkedbear.mybatis.entity.Department;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentTypeHandler implements TypeHandler<Department> {
    
    @Override
    public void setParameter(PreparedStatement ps, int i, Department department, JdbcType jdbcType) throws SQLException {
        ps.setString(i, department.getId());
    }
    
    @Override
    public Department getResult(ResultSet rs, String columnName) throws SQLException {
        Department department = new Department();
        department.setId(rs.getString(columnName));
        return department;
    }
    
    @Override
    public Department getResult(ResultSet rs, int columnIndex) throws SQLException {
        Department department = new Department();
        department.setId(rs.getString(columnIndex));
        return department;
    }
    
    @Override
    public Department getResult(CallableStatement cs, int columnIndex) throws SQLException {
        Department department = new Department();
        department.setId(cs.getString(columnIndex));
        return department;
    }
}
