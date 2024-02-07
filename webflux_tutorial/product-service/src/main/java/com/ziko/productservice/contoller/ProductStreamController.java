package com.ziko.productservice.contoller;

import com.ziko.productservice.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 07 Feb, 2024
 */

@RestController
@RequestMapping(path = "product-sse")
@RequiredArgsConstructor
public class ProductStreamController {

    private final Flux<ProductDto> productBroadcast;

    @GetMapping(value = "stream/{maxPrice}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ProductDto> getProductUpdate(@PathVariable("maxPrice") int maxPrice){
        return this.productBroadcast
//                .distinct(ProductDto::price)
                .distinctUntilChanged(ProductDto::id)
                .filter(dto -> dto.price() <= maxPrice);
    }
}
