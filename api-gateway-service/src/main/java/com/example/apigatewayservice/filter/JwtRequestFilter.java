package com.example.apigatewayservice.filter;

import com.example.apigatewayservice.util.JwtUtil;
import com.example.apigatewayservice.util.PermitURIs;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class JwtRequestFilter implements WebFilter {

    private final JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        // 요청 URI 를 filterSkipPath 에 저장
        String filterSkipPath = exchange.getRequest().getURI().getPath();

        // 로그인, 회원가입은 필터적용 X
        if (PermitURIs.SKIP_URIS.stream().anyMatch(filterSkipPath::contains)) {
            // 다음 필터로 요청 전달(해당 필터 스킵하고 진행)
            return chain.filter(exchange);
        }

        // 헤더에서 Authorization 키에 JWT 토큰 추출
        String authorizationHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        // null 이거나 Bearer 로 시작 안하면 401 에러 반환
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // JWT 토큰만 추출
        String token = authorizationHeader.substring(7);

        // JWT 토큰에 담겨있는 email 추출
        String email = jwtUtil.extractClaim(token, "email");
        String userRole = jwtUtil.extractClaim(token, "role");

        if (email != null) {
            UserDetails userDetails = User.withUsername(email)
                    .password("")
                    .authorities(userRole)
                    .build();

            Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            return chain.filter(exchange)
                    .contextWrite(ReactiveSecurityContextHolder.withAuthentication(auth));      // 리액티브 환경에서 보안 컨텍스트를 설정
        } else {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }
}
