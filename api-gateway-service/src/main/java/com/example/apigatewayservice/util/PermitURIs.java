package com.example.apigatewayservice.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermitURIs {

    //공통 URI 리스트
    public static final List<String> URIs = List.of(
            "/error",
            "/member/error",
            "/members/auth/signIn",
            "/members/auth/signUp",
            "/members/auth/",
            "/members/auth/**",
            "/jwt/refresh"
            );

    // Security 설정에서 사용할 배열
    public static final String[] PERMIT_ALL = URIs.toArray(new String[0]);


    // Filter 설정에서 사용할 Set
    public static final Set<String> SKIP_URIS = new HashSet<>(URIs);

}
