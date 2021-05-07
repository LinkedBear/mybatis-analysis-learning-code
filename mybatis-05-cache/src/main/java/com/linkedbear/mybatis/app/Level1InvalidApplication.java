package com.linkedbear.mybatis.app;

import com.linkedbear.mybatis.mapper.DepartmentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Level1InvalidApplication {
    
    public static void main(String[] args) throws Exception {
        InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        
        // 跨SqlSession的一级缓存不共享
        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
        DepartmentMapper departmentMapper2 = sqlSession2.getMapper(DepartmentMapper.class);
        
        // 此种情况会开启两个Connection连接，打印两次相同的SQL
        departmentMapper.findAll();
        departmentMapper2.findAll();
    
        System.out.println("重复调用findAll方法......");
        departmentMapper.findAll();
        System.out.println("手动清空SqlSession的缓存......");
        sqlSession.clearCache();
        System.out.println("清空缓存后重新调用findAll方法......");
        departmentMapper.findAll();
        System.out.println("--------------------------------");
        
        sqlSession.close();
        sqlSession2.close();
        
        System.out.println("=============================================");
    
        SqlSession sqlSessionWithoutTransaction = sqlSessionFactory.openSession(true);
        DepartmentMapper departmentMapperWithoutTransaction = sqlSessionWithoutTransaction.getMapper(DepartmentMapper.class);
        
        departmentMapperWithoutTransaction.findAll();
        departmentMapperWithoutTransaction.findAll();
        
        sqlSessionWithoutTransaction.close();
    }
}
