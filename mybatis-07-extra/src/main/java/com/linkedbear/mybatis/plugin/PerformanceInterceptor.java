package com.linkedbear.mybatis.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.jdbc.PreparedStatementLogger;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Intercepts({
    @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
    @Signature(type = StatementHandler.class, method = "update", args = {Statement.class})
})
public class PerformanceInterceptor implements Interceptor {
    
    private long maxTolerate;
    
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("PerformanceInterceptor intercept run ......");
        long startTime = System.currentTimeMillis();
        Object retVal = invocation.proceed();
        long endTime = System.currentTimeMillis();
        if (endTime - startTime > maxTolerate) {
            Statement statement = (Statement) invocation.getArgs()[0];
            // statement被MyBatis代理了一层，需要取到target
            Field targetField = statement.getClass().getSuperclass().getDeclaredField("h");
            targetField.setAccessible(true);
            PreparedStatementLogger target = (PreparedStatementLogger) targetField.get(statement);
            PreparedStatement preparedStatement = target.getPreparedStatement();
            String statementToString = preparedStatement.toString();
            System.out.println("发现慢SQL：" + getSql(statementToString));
            System.out.println("执行时间：" + (endTime - startTime) + "ms");
        }
        return retVal;
    }
    
    private String getSql(String statementToString) {
        Pattern pattern = Pattern.compile("(select |insert |update |delete ).*");
        Matcher matcher = pattern.matcher(statementToString);
        if (matcher.find()) {
            return matcher.group();
        }
        return statementToString;
    }
    
    @Override
    public void setProperties(Properties properties) {
        this.maxTolerate = Long.parseLong(properties.getProperty("maxTolerate"));
    }
}
