package com.linkedbear.mybatis.app;

import com.linkedbear.mybatis.entity.User;
import com.linkedbear.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class DepartmentTypeHandlerApplication {
    
    public static void main(String[] args) throws Exception {
        InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);
        SqlSession sqlSession = sqlSessionFactory.openSession();
    
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findAllUseTypeHandler();
        userList.forEach(System.out::println);

//        User user = new User();
//        user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
//        user.setName("hahahaha");
//        Department department = new Department();
//        department.setId("18ec781fbefd727923b0d35740b177ab");
//        user.setDepartment(department);
//        userMapper.saveUser(user);
//
//        sqlSession.commit();
//        sqlSession.close();
    }
}
