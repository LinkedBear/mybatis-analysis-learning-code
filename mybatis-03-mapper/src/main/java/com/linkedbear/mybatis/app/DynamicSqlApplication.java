package com.linkedbear.mybatis.app;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class DynamicSqlApplication {
    
    public static void main(String[] args) throws Exception {
        InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);
        SqlSession sqlSession = sqlSessionFactory.openSession();
    
//        Department department = new Department();
//        department.setName("产品");
//        List<Department> departmentList = sqlSession.selectList("dynamic.findAllDepartmentUseBind", department);
//        departmentList.forEach(System.out::println);
        
//        department.setName("测试部");
//        department.setTel("12345679");
//        sqlSession.update("dynamic.updateDepartment", department);
    
//        BeanMap beanMap = BeanMap.create(department);
//        Map<String, Object> departmentMap = new HashMap<>(2);
//        departmentMap.put("id", "53e3803ebbf4f97968e0253e5ad4cc83");
//        departmentMap.put("beanMap", beanMap);
//        sqlSession.update("dynamic.updateDepartmentByMap", departmentMap);
    
        List<Object> list = sqlSession.selectList("dynamic.findAllUseSql", Collections.emptyMap());
        list.forEach(System.out::println);
    }
}
