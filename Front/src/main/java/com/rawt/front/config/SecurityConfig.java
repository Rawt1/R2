package com.rawt.front.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(auth -> {
            try {
                auth.anyRequest().authenticated()
                        .and()
                        .oauth2Login(oauth -> {
                            oauth.authorizationEndpoint(authorizationEndpointConfig -> {
                                authorizationEndpointConfig.baseUri("/login/oauth2/code/authserver");
                            });
                        });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return http.build();
    }
}
