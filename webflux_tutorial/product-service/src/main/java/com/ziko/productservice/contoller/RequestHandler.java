package com.ziko.productservice.contoller;

import com.ziko.productservice.dto.ProductDto;
import com.ziko.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.lang.reflect.Type;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 08 Nov, 2023
 */

@Component
@RequiredArgsConstructor
public class RequestHandler {

    private final ProductService service;

    public Mono<ServerResponse> getAllHandler(ServerRequest serverRequest){
        return ServerResponse.ok().body(this.service.getAll(), ProductDto.class);
    }

    public Mono<ServerResponse> getByIdHandler(ServerRequest serverRequest){
        simulateRandomException();
        String id = serverRequest.pathVariable("id");
        return this.service.getProductById(id)
                .flatMap(productDto -> ServerResponse.ok().bodyValue(productDto))
                .switchIfEmpty(ServerResponse.notFound().build());
//        return ServerResponse.ok().body(, ProductDto.class)
//                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> insertProductHandler(ServerRequest serverRequest){
        Mono<ProductDto> productDtoMono = serverRequest.bodyToMono(ProductDto.class);
        return ServerResponse.ok().body(this.service.insertProduct(productDtoMono), ProductDto.class);
    }

    public Mono<ServerResponse> updateProductHandler(ServerRequest serverRequest){
        String id = serverRequest.pathVariable("id");
        Mono<ProductDto> productDtoMono = serverRequest.bodyToMono(ProductDto.class);
        return ServerResponse.ok().body(this.service.updateProduct(id, productDtoMono), ProductDto.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteProductHandler(ServerRequest serverRequest){
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok().body(this.service.deleteProduct(id), new ParameterizedTypeReference<>() {});
    }

    public Mono<ServerResponse> productPriceRangeHandler(ServerRequest serverRequest){
        Optional<String> min = serverRequest.queryParam("min");
        Optional<String> max = serverRequest.queryParam("max");
        return ServerResponse.ok().body(this.service.priceRange(min.orElse(null), max.orElse(null)), new ParameterizedTypeReference<>() {});
    }

    private void simulateRandomException(){
        int nextInt = ThreadLocalRandom.current().nextInt(1, 10);
        if(nextInt > 5) throw new RuntimeException("Something is wrong");
    }
}
