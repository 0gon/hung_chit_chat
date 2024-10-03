package com.memberservice.member.domain;

import com.memberservice.member.service.port.IdentifierFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDFactory implements IdentifierFactory {


    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
