package com.praveen.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;

import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)

            // Disable browser popup
            .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)

            // Disable form login
            .formLogin(ServerHttpSecurity.FormLoginSpec::disable)

            .authorizeExchange(exchange -> exchange
                    .pathMatchers(
                            "/auth/**",
                            "/swagger-ui/**",
                            "/v3/api-docs/**"
                    ).permitAll()
                    .anyExchange().authenticated()
            )
            .build();
    }
}