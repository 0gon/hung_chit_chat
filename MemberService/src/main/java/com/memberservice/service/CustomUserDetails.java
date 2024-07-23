package com.memberservice.service;

import com.memberservice.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(member.getRole().name()));
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    // 계정이 만료되었는지
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겼는지
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 자격 증명이 만료되었는지
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화되었는지
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
