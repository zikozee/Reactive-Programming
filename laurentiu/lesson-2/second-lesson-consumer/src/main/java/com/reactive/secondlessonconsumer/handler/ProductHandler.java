package com.reactive.secondlessonconsumer.handler;

import com.reactive.secondlessonconsumer.model.Product;
import com.reactive.secondlessonconsumer.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

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


    public Mono<ServerResponse> all(ServerRequest serverRequest){
      log.info(serverRequest.methodName());

      return ok()
              .contentType(MediaType.TEXT_EVENT_STREAM)
              .body(productService.getAll(), Product.class);

    }

    public  Mono<ServerResponse> create(ServerRequest serverRequest){
        return serverRequest
                .bodyToMono(Product.class)
                .flatMap(this.productService::createProduct)
                .flatMap(p -> ServerResponse.created(URI.create("/create/" + p.getName())) //URI is for location in HEADERS
                        .contentType(MediaType.TEXT_EVENT_STREAM)
                        .body(BodyInserters.fromValue(p)));
    }
}
