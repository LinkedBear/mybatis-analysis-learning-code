package com.linkedbear.mybatis.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcFetchSizeApplication {
    
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8", "root", "123456");
        PreparedStatement ps = connection.prepareStatement("select * from tbl_department");
        ps.setFetchSize(10);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name"));
        }
        resultSet.close();
        ps.close();
        connection.close();
    }
}
