package com.rawt.front.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            try {
                auth
                        .requestMatchers(HttpMethod.GET, "/", "/error").permitAll()
                        .requestMatchers(HttpMethod.GET, "/css/**", "/js/**", "/images/**", "/webjars/**", "/favicon.ico").permitAll()
                        .requestMatchers(HttpMethod.GET, "/debug/**").permitAll()
                        .anyRequest().authenticated()
                        .and().logout().logoutSuccessUrl("/").and()
                        .oauth2Login(oauth -> {
                            oauth.authorizationEndpoint(authorizationEndpointConfig -> {
                                authorizationEndpointConfig.baseUri("/login/oauth2/code/authserver");
                            });
                            oauth.defaultSuccessUrl("/");
                        });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return JwtDecoders.fromIssuerLocation("http://localhost:9000");
    }

    @Bean
    public GrantedAuthoritiesMapper grantedAuthoritiesMapper() {
        return authorities -> {
            Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
            authorities.forEach(grantedAuthority -> {
                if (grantedAuthority instanceof OidcUserAuthority) {
                    OidcUserAuthority oidcUserAuthority = (OidcUserAuthority) grantedAuthority;
                    OidcIdToken idToken = oidcUserAuthority.getIdToken();
                    Map<String, Object> claims = idToken.getClaims();
                    if (claims.containsKey("roles")) {

                        List<String> roles = (List<String>) claims.get("roles");
                        roles.forEach(r->grantedAuthoritySet.add(new SimpleGrantedAuthority(r)));
                    }
                } else if (grantedAuthority instanceof SimpleGrantedAuthority) {
                    grantedAuthoritySet.add(grantedAuthority);
                }
            });
            return grantedAuthoritySet;
        };
    }
}
