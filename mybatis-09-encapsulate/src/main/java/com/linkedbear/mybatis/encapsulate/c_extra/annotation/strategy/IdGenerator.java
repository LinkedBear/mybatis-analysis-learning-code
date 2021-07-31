package com.linkedbear.mybatis.encapsulate.c_extra.annotation.strategy;

public interface IdGenerator<T> {
    
    T next();
}
