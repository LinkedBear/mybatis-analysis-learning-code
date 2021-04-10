package com.linkedbear.mybatis.mapper;

import com.linkedbear.mybatis.entity.Department;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {
    
    private SqlSessionFactory sqlSessionFactory;
    
    public DepartmentDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
    
    @Override
    public List<Department> findAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectList("com.linkedbear.mybatis.mapper.DepartmentMapper.findAll");
        }
    }
    
    @Override
    public Department findById(String id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectOne("com.linkedbear.mybatis.mapper.DepartmentMapper.findById", id);
        }
    }
}
