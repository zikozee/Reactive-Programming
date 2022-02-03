package com.reactive.secondlessonconsumer.service;

import com.reactive.secondlessonconsumer.handler.ProductHandler;
import com.reactive.secondlessonconsumer.model.Product;
import com.reactive.secondlessonconsumer.proxy.ProductProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author : Ezekiel Eromosei
 * @created : 03 Feb, 2022
 */

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductProxy proxy;

    public Flux<Product> getAll(){
        return proxy.getAll();
    }


    public Mono<Product> createProduct(Product product){
        return proxy.createProduct(product);
    }
}
