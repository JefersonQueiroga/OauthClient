package com.example.oauthclient.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {// @formatter:off
        http.authorizeRequests()
            .antMatchers("/", "/oauth/**","/api/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login();
    }
}
