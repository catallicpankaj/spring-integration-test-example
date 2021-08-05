package com.example.pankajtechblog.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfigs {

    @Bean
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }
}
