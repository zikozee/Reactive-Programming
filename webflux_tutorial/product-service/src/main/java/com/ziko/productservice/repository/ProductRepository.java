package com.ziko.productservice.repository;

import com.ziko.productservice.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 08 Nov, 2023
 */

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

//    Flux<Product> findAllByPriceBetween(int price1, int price2);
    Flux<Product> findAllByPriceBetween(Range<Integer> range);
}
