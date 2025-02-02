package com.ecommerce.platform.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OrderWebClient {


    @Bean
    public WebClient userWebClient() {
        return WebClient.builder().baseUrl("http://localhost:8082").build();
    }
}
