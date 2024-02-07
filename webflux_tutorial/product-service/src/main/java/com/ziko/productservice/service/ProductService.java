package com.ziko.productservice.service;

import com.ziko.productservice.dto.ProductDto;
import com.ziko.productservice.repository.ProductRepository;
import com.ziko.productservice.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 08 Nov, 2023
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final Sinks.Many<ProductDto> sink;

    public Flux<ProductDto> getAll() {
        return this.repository.findAll().map(EntityDtoUtil::toProductDto);
    }

    public Mono<ProductDto> getProductById(String id) {
        return this.repository.findById(id).map(EntityDtoUtil::toProductDto);
    }

    public Mono<ProductDto> insertProduct(Mono<ProductDto> productDtoMono) {
        return productDtoMono.flatMap(productDto -> repository.insert(EntityDtoUtil.toProduct(productDto)))  //or .save()
                .map(EntityDtoUtil::toProductDto)
                .doOnNext(this.sink::tryEmitNext);
    }

    public Mono<ProductDto> updateProduct(String id, Mono<ProductDto> productDtoMono) {
        return this.repository.findById(id)
                .flatMap(product -> productDtoMono
                        .map(EntityDtoUtil::toProduct)
                        .doOnNext(e -> e.setId(id))
                )
                .flatMap(this.repository::save)
                .map(EntityDtoUtil::toProductDto);
    }

    public Mono<Void> deleteProduct(String id){
        return this.repository.deleteById(id);
    }

    public Flux<ProductDto> priceRange(String min, String max) {
        return this.repository.findAllByPriceBetween(Range.closed(Integer.parseInt(min), Integer.parseInt(max)))
                .map(EntityDtoUtil::toProductDto);
    }
}
