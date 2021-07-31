package com.linkedbear.mybatis.encapsulate.c_extra.annotation.strategy;

import java.util.UUID;

public class UuidGenerator implements IdGenerator<String> {
    
    @Override
    public String next() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
