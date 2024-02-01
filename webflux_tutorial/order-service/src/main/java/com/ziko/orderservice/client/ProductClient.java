package com.ziko.orderservice.client;

import com.ziko.orderservice.dto.ProductDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 01 Feb, 2024
 */

@Service
public class ProductClient {

    private final WebClient webClient;


    public ProductClient(@Value("${product.service.url}") String url) {
        webClient = WebClient.builder()
                .baseUrl(url)
                .build();
    }

    public Mono<ProductDto> getProductById(final String productId){
        return this.webClient
                .get()
                .uri("{id}", productId)
                .retrieve()
                .bodyToMono(ProductDto.class);
    }
}
