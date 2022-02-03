package com.reactive.secondlessonconsumer.proxy;

import com.reactive.secondlessonconsumer.handler.ProductHandler;
import com.reactive.secondlessonconsumer.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : Ezekiel Eromosei
 * @created : 03 Feb, 2022
 */

@Component
@RequiredArgsConstructor
public class ProductProxy {

    private final WebClient webClient;

    public Flux<Product> getAll(){
        return webClient.get().uri("/products")
                .exchangeToFlux(res -> res.bodyToFlux(Product.class));
    }

    public Mono<Product> createProduct(Product product){
        return webClient.post().uri("/products")
                .body(BodyInserters.fromValue(product))
                .exchangeToMono(res -> res.bodyToMono(Product.class));
    }
}
