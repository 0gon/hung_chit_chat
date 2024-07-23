package com.memberservice.config;


import com.memberservice.Permit_URLs;
import com.memberservice.jwt.JwtRequestFilter;
import com.memberservice.jwt.JwtUtil;
import com.memberservice.service.CustomUserDetails;
import com.memberservice.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    /**
     * H2 는 Security 에 안걸리게
     * */
    @Bean
    @ConditionalOnProperty(name = "spring.h2.console.enalbed", havingValue = "true")
    public WebSecurityCustomizer webSecurityCustomizer() { // h2
        return web -> web.ignoring().requestMatchers(PathRequest.toH2Console());
    }

    @Bean
    protected SecurityFilterChain springSecurityFilterChain(HttpSecurity http, JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request) -> request
                        .requestMatchers(Permit_URLs.PERMIT_ALL).permitAll()
                        .anyRequest().authenticated())
                .headers((headers) -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .addFilterBefore(new JwtRequestFilter(jwtUtil, customUserDetailsService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
