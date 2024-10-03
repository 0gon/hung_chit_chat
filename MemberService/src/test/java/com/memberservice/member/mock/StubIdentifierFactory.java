package com.memberservice.member.mock;

import com.memberservice.member.service.port.IdentifierFactory;

public class StubIdentifierFactory implements IdentifierFactory {

    @Override
    public String generate() {
        return "aaa-bbb-ccc";
    }
}
