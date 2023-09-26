package com.rawt.front.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/debug")
public class DebugController {

    @GetMapping("/tokens")
    public String getTokens(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
    }
}
