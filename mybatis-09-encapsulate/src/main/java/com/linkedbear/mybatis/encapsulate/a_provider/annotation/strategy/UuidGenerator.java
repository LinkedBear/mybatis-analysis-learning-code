package com.linkedbear.mybatis.encapsulate.a_provider.annotation.strategy;

import java.util.UUID;

public class UuidGenerator implements IdGenerator<String> {
    
    @Override
    public String next() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
