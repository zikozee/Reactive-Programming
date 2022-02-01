package com.reactive.first_lesson.service;

import com.reactive.first_lesson.model.Product;
import com.reactive.first_lesson.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author : Ezekiel Eromosei
 * @created : 01 Feb, 2022
 */

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public Flux<Product> fetchAll(){
        return productRepository.findAll()
                .delayElements(Duration.ofSeconds(5));
    }

    public Mono<Product> createProduct(Product product){
        return productRepository.save(product);
    }
}
