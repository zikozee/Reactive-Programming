package com.reactive.first_lesson.controller;

import com.reactive.first_lesson.model.Product;
import com.reactive.first_lesson.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : Ezekiel Eromosei
 * @created : 01 Feb, 2022
 */

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(path = "products",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Product> createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @GetMapping(path = "products", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> fetchAllProduct(){
        return productService.fetchAll();
    }
}
