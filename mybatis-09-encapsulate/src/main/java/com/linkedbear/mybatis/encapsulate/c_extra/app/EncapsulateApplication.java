package com.linkedbear.mybatis.encapsulate.c_extra.app;

import com.github.pagehelper.PageHelper;
import com.linkedbear.mybatis.encapsulate.c_extra.DepartmentMapper;
import com.linkedbear.mybatis.encapsulate.c_extra.entity.Department;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class EncapsulateApplication {
    
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("c_extra/spring-mybatis.xml");
        DepartmentMapper departmentMapper = ctx.getBean(DepartmentMapper.class);
//        List<Department> departmentList = departmentMapper.findAll();
    
//        Department department = new Department();
//        department.setName("test encapsulate");
//        department.setTel("12345");
//        departmentMapper.insert(department);

//        Department department = departmentList.get(departmentList.size() - 1);
//        departmentMapper.delete(department);
    
//        Department department = departmentList.get(departmentList.size() - 1);
//        department.setTel("54321111");
//        departmentMapper.update(department);
        
//        Department example = new Department();
//        example.setId("18ec781fbefd727923b0d35740b177ab");
//        example.setName("部");
//        departmentMapper.findByExample(example);
    
        PageHelper.startPage(1, 2);
        List<Department> departmentList = departmentMapper.findAll();
        System.out.println(departmentList);
    }
}
