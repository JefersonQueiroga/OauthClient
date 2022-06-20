package com.example.oauthclient.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientOAuth2Config {

    @Bean
    ReactiveClientRegistrationRepository clientRegistrations(
            @Value("${spring.security.oauth2.client.provider.pipedrive.token-uri}") String token_uri,
            @Value("${spring.security.oauth2.client.registration.pipedrive.client-id}") String client_id,
            @Value("${spring.security.oauth2.client.registration.pipedrive.client-secret}") String client_secret,
            @Value("${spring.security.oauth2.client.registration.pipedrive.redirect-uri}") String redirect,
            @Value("${spring.security.oauth2.client.registration.pipedrive.authorization-grant-type}") String authorizationGrantType,
            @Value("${spring.security.oauth2.client.registration.pipedrive.scope}") String scope,
            @Value("${spring.security.oauth2.client.provider.pipedrive.authorization-uri}") String authorizationUrl,
            @Value("${spring.security.oauth2.client.provider.pipedrive.user-info-uri}") String useInfo


    ) {
        ClientRegistration registration = ClientRegistration.withRegistrationId("pipedrive").tokenUri(token_uri)
                .clientId(client_id).clientSecret(client_secret)
                .redirectUri(redirect)
                .authorizationUri(authorizationUrl)
                .userInfoUri(useInfo)
                .authorizationGrantType(new AuthorizationGrantType(authorizationGrantType)).build();
        System.out.println(registration.toString());
        return new InMemoryReactiveClientRegistrationRepository(registration);
    }

    @Bean
    WebClient webClient(ReactiveClientRegistrationRepository clientRegistrations) {
        InMemoryReactiveOAuth2AuthorizedClientService clientService = new InMemoryReactiveOAuth2AuthorizedClientService(
                clientRegistrations);
        AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager authorizedClientManager = new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(
                clientRegistrations, clientService);
        ServerOAuth2AuthorizedClientExchangeFilterFunction oauth = new ServerOAuth2AuthorizedClientExchangeFilterFunction(
                authorizedClientManager);
        oauth.setDefaultClientRegistrationId("pipedrive");

        return WebClient.builder().filter(oauth).build();
    }

}
