package com.linkedbear.mybatis.app;

import com.linkedbear.mybatis.entity.Department;
import com.linkedbear.mybatis.mapper.DepartmentMapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyBatisSpringApplication {
    
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        
        DepartmentMapper departmentMapper = ctx.getBean(DepartmentMapper.class);
        List<Department> departmentList = departmentMapper.findAll();
        departmentList.forEach(System.out::println);
        
        ctx.close();
    }
}
