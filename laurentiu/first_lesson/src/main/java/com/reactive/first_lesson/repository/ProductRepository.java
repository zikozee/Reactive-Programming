package com.reactive.first_lesson.repository;

import com.reactive.first_lesson.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author : Ezekiel Eromosei
 * @created : 01 Feb, 2022
 */

public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {
}
