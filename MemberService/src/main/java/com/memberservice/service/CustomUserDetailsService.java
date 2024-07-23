package com.memberservice.service;

import com.memberservice.entity.Member;
import com.memberservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // TTL 설정  -> Cache
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member findmember = memberRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        return new CustomUserDetails(findmember);
    }

}
