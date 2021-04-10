package com.linkedbear.mybatis.app;

import com.linkedbear.mybatis.entity.Department;
import com.linkedbear.mybatis.mapper.DepartmentMapper;
import com.linkedbear.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class SelectUseCacheApplication {
    
    public static void main(String[] args) throws Exception {
        InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);
    
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
        Department department = departmentMapper.findById("18ec781fbefd727923b0d35740b177ab");
        System.out.println(department);
        Department department2 = departmentMapper.findById("18ec781fbefd727923b0d35740b177ab");
        System.out.println("department == department2 : " + (department == department2));
        sqlSession.close();
        
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        DepartmentMapper departmentMapper2 = sqlSession2.getMapper(DepartmentMapper.class);
        Department department3 = departmentMapper2.findById("18ec781fbefd727923b0d35740b177ab");
        departmentMapper2.findAll();
    
        UserMapper userMapper = sqlSession2.getMapper(UserMapper.class);
        userMapper.cleanCache();
        System.out.println("==================cleanCache====================");
        
        Department department4 = departmentMapper2.findById("18ec781fbefd727923b0d35740b177ab");
        System.out.println("department3 == department4 : " + (department3 == department4));
        departmentMapper2.findAll();
    
        sqlSession2.close();
    }
}
