package com.linkedbear.mybatis.encapsulate.a_provider.annotation.strategy;

public interface IdGenerator<T> {
    
    T next();
}
