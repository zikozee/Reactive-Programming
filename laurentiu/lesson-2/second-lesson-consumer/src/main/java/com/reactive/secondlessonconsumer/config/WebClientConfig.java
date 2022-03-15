package com.reactive.secondlessonconsumer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author : Ezekiel Eromosei
 * @created : 03 Feb, 2022
 */

@Configuration
public class WebClientConfig {

    @Value("${product.service.base.url}")
    private String baseUrl;

    @Bean
    public WebClient webClient(){
        return WebClient
                .builder()
                .baseUrl(baseUrl)
                .build();
    }
}
