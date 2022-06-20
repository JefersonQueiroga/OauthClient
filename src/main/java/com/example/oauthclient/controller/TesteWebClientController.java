package com.example.oauthclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Controller
public class TesteWebClientController {

    @Autowired
    WebClient webClient;

    @GetMapping("/api/user")
    Mono<String> useOauthWithAuthCode() {
        Mono<String> retrievedResource = webClient.get()
                .uri("https://api.pipedrive.com/v1/users/me")
                .retrieve()
                .bodyToMono(String.class);
        return retrievedResource.map(string -> "Info:" + string);
    }

}
