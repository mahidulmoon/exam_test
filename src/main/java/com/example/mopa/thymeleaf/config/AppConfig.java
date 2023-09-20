package com.example.mopa.thymeleaf.config;

import com.example.mopa.thymeleaf.Utils.AuthLoggedUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public AuthLoggedUser getLoggingUser(){
        return new AuthLoggedUser();
    }

}
