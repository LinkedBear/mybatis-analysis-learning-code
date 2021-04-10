package com.linkedbear.mybatis.app;

import com.linkedbear.mybatis.entity.Department;
import com.linkedbear.mybatis.entity.User;
import com.linkedbear.mybatis.mapper.DepartmentMapper;
import com.linkedbear.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class ResultMapApplication {
    
    public static void main(String[] args) throws Exception {
        InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        
        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
        List<Department> departmentList = departmentMapper.findAll();
        departmentList.forEach(System.out::println);
    
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findAll();
        userList.forEach(System.out::println);
        
        List<User> userList2 = sqlSession.selectList("com.linkedbear.mybatis.mapper.UserMapper.findAllUseDiscriminator");
        userList2.forEach(System.out::println);
    }
}
