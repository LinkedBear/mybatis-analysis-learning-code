package com.linkedbear.mybatis.encapsulate.b_metadata.app;

import com.linkedbear.mybatis.encapsulate.b_metadata.DepartmentMapper;
import com.linkedbear.mybatis.encapsulate.b_metadata.entity.Department;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EncapsulateApplication {
    
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("b_metadata/spring-mybatis.xml");
        DepartmentMapper departmentMapper = ctx.getBean(DepartmentMapper.class);
        departmentMapper.findAll();
    
        Department department = new Department();
        department.setName("test encapsulate");
        department.setTel("12345");
        departmentMapper.insert(department);
    }
}
