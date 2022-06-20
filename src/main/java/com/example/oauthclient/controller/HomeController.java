package com.example.oauthclient.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/oauth/callback")
    public ResponseEntity<?> getAuthorizationCode(@RequestParam("code") String code, @RequestParam("state") String state){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        System.out.println("====== Nome:" + name);
        return ResponseEntity.ok().body(code + " - " + state);
    }

    @GetMapping("/api/teste")
    public ResponseEntity<?> apiTeste() {
        return ResponseEntity.ok().body("Hello!!!!!");
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAttributes();
    }
}