package com.reactive.secondlesson.handler;

import com.reactive.secondlesson.model.Product;
import com.reactive.secondlesson.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @author : Ezekiel Eromosei
 * @created : 03 Feb, 2022
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductHandler {

    private final ProductService productService;

    public  Mono<ServerResponse> getAll(ServerRequest serverRequest, ProductService productService){ // direct injection
        log.info(String.valueOf(serverRequest.method()));
        return ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(productService.getProducts(), Product.class);
    }

    public  Mono<ServerResponse> getAll(ServerRequest serverRequest){
        log.info(String.valueOf(serverRequest.method()));
        return ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(productService.getProducts(), Product.class);
    }
}
