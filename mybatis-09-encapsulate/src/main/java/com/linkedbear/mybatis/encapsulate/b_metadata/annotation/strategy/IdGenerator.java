package com.linkedbear.mybatis.encapsulate.b_metadata.annotation.strategy;

public interface IdGenerator<T> {
    
    T next();
}
