package com.linkedbear.mybatis.encapsulate.b_metadata.annotation;

import com.linkedbear.mybatis.encapsulate.b_metadata.annotation.strategy.IdGenerator;
import com.linkedbear.mybatis.encapsulate.b_metadata.annotation.strategy.UuidGenerator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Id {
    
    Class<? extends IdGenerator> generateStrategy() default UuidGenerator.class;
}
