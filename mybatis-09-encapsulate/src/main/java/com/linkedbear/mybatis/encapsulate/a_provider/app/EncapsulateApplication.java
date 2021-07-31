package com.linkedbear.mybatis.encapsulate.a_provider.app;

import com.linkedbear.mybatis.encapsulate.a_provider.DepartmentMapper;
import com.linkedbear.mybatis.encapsulate.a_provider.entity.Department;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class EncapsulateApplication {
    
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("a_provider/spring-mybatis.xml");
        DepartmentMapper departmentMapper = ctx.getBean(DepartmentMapper.class);
        List<Department> departmentList = departmentMapper.findAll();
    
        //        Department department = new Department();
//        department.setName("test encapsulate");
//        department.setTel("12345");
//        departmentMapper.insert(department);
    
        Department department = departmentList.get(departmentList.size() - 1);
        department.setTel("54321");
        departmentMapper.update(department);
    }
}
