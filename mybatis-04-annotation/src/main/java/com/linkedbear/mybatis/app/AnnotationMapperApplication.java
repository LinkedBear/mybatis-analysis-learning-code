package com.linkedbear.mybatis.app;

import com.linkedbear.mybatis.entity.Department;
import com.linkedbear.mybatis.mapper.DepartmentAnnotationMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class AnnotationMapperApplication {
    
    public static void main(String[] args) throws Exception {
        InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        
        DepartmentAnnotationMapper departmentMapper = sqlSession.getMapper(DepartmentAnnotationMapper.class);
    
        /*
        List<Department> departmentList = departmentMapper.findAll();
        departmentList.forEach(System.out::println);
    
        Department department = departmentMapper.findById("18ec781fbefd727923b0d35740b177ab");
        System.out.println(department);
        
        Department example = new Department();
        example.setName("全部");
        List<Department> departmentListByExample = departmentMapper.findAllByExample(example);
        System.out.println(departmentListByExample);
    
        List<Department> departmentByResultsList = departmentMapper.findAllByResults();
        departmentByResultsList.forEach(System.out::println);
         */
        
        /*
        Department department = new Department();
        department.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        department.setName("测试部门~");
        department.setTel("123456789");
        departmentMapper.save(department);
    
        Department department = new Department();
        department.setName("测试部门~");
        department.setTel("123456789");
        departmentMapper.saveUseGeneratedKeys(department);
        System.out.println(department);
        */
    
        Department department = departmentMapper.findById("11c8cdec37e041cf8476c86d46a42dd3");
        department.setName("测测试试");
        departmentMapper.updateById(department);
        
        departmentMapper.deleteById("11c8cdec37e041cf8476c86d46a42dd3");
    
        sqlSession.commit();
        sqlSession.close();
    }
}
