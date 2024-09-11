package com.example.apigatewayservice.filter;

import com.example.apigatewayservice.util.JwtVerificationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtVerificationFilter extends AbstractGatewayFilterFactory {

    private final JwtVerificationUtil jwtVerificationUtil;

    @Override
    public GatewayFilter apply(Object config) {
        return ((exchange, chain) -> {

            ServerHttpRequest request = exchange.getRequest();
            String authorization = request.getHeaders().getFirst("Authorization");

            if(authorization == null || !authorization.startsWith("Bearer ")) {
                return unauthorized(exchange);
            }

            // Bearer 이후 jwt 토큰 추출
            String jwtToken = authorization.replace("Bearer ", "");

            // 비동기적 체이닝 방식 사용 -> Spring cloud Gateway 에선 비동기 방식이 "필수", 동기 처리는 불가능 -> flatMap, then 메서드등 사용해서 처리 가능
            return jwtVerificationUtil.validateToken(jwtToken)
                    .flatMap(isValid -> {
                        if (isValid) {
                            // 토큰이 유효할 경우 체이닝을 통해 다음 필터로 전달
                            return chain.filter(exchange);
                        } else {
                            // 토큰이 유효하지 않을 경우 401 Unauthorized 응답
                            return unauthorized(exchange);
                        }
                    });
        });
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);      // 401 unauthorized 상태 코드 SET
        return exchange.getResponse().setComplete();                        // 빈 응답 반환
    }
}
