package com.memberservice.config;

import com.memberservice.PermitURIs;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String filterSkipPath = request.getRequestURI();

        // 로그인, 회원가입은 필터적용 X, anyMatch - 하나라도 포함되면 더 이상 실행 X
        if(PermitURIs.SKIP_URIS.stream().anyMatch(filterSkipPath::contains)){
            filterChain.doFilter(request, response);
            return;
        }

        final String authorizationHeader = request.getHeader("Authorization");        // Header 에서 Authorization 정보를 가져옴

        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            // Bearer 타입이 아니거나, 토큰이 없으면 401 Unauthorized 응답
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String token = authorizationHeader.substring(7);
        String username = jwtUtil.extractUsername(token);

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            
                // UserDetails 생성
               UserDetails userDetails = User.withUsername(username)
                       .password("")
                       .authorities(Collections.emptyList())
                       .build();

                   UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                           new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                   SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        } else{
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }

        filterChain.doFilter(request, response);

    }
}
