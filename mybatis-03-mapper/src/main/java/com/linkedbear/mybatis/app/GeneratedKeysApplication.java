package com.linkedbear.mybatis.app;

import com.linkedbear.mybatis.entity.Department;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class GeneratedKeysApplication {
    
    public static void main(String[] args) throws Exception {
        InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);
        SqlSession sqlSession = sqlSessionFactory.openSession();
    
        Department department = new Department();
        department.setName("hahaha");
        department.setTel("12345");
        sqlSession.insert("test.save", department);
        sqlSession.commit();
    
        System.out.println(department);
    }
}
