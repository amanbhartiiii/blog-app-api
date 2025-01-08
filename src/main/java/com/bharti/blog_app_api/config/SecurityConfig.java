package com.bharti.blog_app_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf((csrf)-> csrf.disable())  //Disable CSRF
                .authorizeHttpRequests((req)-> req.anyRequest().authenticated())   // Require authentication for all requests
                .httpBasic(https-> {});   // Enable HTTP Basic authentication

        return httpSecurity.build();
    }
}
