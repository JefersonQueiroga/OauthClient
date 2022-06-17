package com.example.oauthclient.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {// @formatter:off
        System.out.println("----------------------");
        http.authorizeRequests()
                .antMatchers("/", "/oauth/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login();
    }
}

