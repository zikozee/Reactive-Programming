package com.reactive.secondlesson.service;

import com.reactive.secondlesson.model.Product;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.time.Duration;
import java.util.List;

/**
 * @author : Ezekiel Eromosei
 * @created : 03 Feb, 2022
 */

@Service
public class ProductService {


    public Flux<Product> getProducts(){
        return Flux.fromIterable(List.of(new Product("Book"), new Product("Chocolate")))
                .delayElements(Duration.ofSeconds(3));
    }

    public Mono<Product> createProduct(Product product){
        return Mono.just(product);
    }

}
