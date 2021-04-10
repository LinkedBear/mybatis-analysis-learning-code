package com.linkedbear.mybatis.app;

import com.linkedbear.mybatis.entity.Department;
import net.sf.cglib.beans.BeanMap;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UpdateForeachApplication {
    
    public static void main(String[] args) throws Exception {
        InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);
        SqlSession sqlSession = sqlSessionFactory.openSession();
    
        Department department = new Department();
        department.setId("53e3803ebbf4f97968e0253e5ad4cc83");
        department.setName("测试部");
        department.setTel("");
    
        BeanMap beanMap = BeanMap.create(department);
        System.out.println(beanMap);
        Map<Object, Object> departmentMap = new HashMap<>(beanMap);
        Iterator<Map.Entry<Object, Object>> entryIterator = departmentMap.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<Object, Object> entry = entryIterator.next();
            if (entry.getValue() == null) {
                entryIterator.remove();
            } else if (entry.getValue() instanceof String && entry.getValue().toString().trim().isEmpty()) {
                entryIterator.remove();
            }
        }
        System.out.println(departmentMap);
//        sqlSession.update("dynamic.updateDepartmentByMap", departmentMap);
    }
}
