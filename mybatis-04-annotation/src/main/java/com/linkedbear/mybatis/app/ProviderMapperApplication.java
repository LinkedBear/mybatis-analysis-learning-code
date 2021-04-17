package com.linkedbear.mybatis.app;

import com.linkedbear.mybatis.mapper.UserAnnotationMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class ProviderMapperApplication {
    
    public static void main(String[] args) throws Exception {
        InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);
        SqlSession sqlSession = sqlSessionFactory.openSession();
    
        UserAnnotationMapper userMapper = sqlSession.getMapper(UserAnnotationMapper.class);
        
        /*
        List<User> userList = userMapper.findAll();
        userList.forEach(System.out::println);
        
        User example = new User();
        example.setName("狗");
        List<User> userByExampleList = userMapper.findAllByExample(example);
        userByExampleList.forEach(System.out::println);
        */
        
        /*
        User user = new User();
        user.setName("阿熊哇哇");
        user.setAge(3);
        Department department = new Department();
        department.setId("18ec781fbefd727923b0d35740b177ab");
        user.setDepartment(department);
        userMapper.save(user);
    
        User user = new User();
        user.setId("fa3fdb1bfd84407c9df5eeedbec65952");
        user.setName("阿熊哈哈");
        userMapper.updateByExample(user);
         */
        
        userMapper.deleteById("fa3fdb1bfd84407c9df5eeedbec65952");
        
        sqlSession.commit();
        sqlSession.close();
    }
}
