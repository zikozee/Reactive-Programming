package com.ziko.webfluxdemo.webclient;

import com.ziko.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 20 Oct, 2023
 */

public class Lecture02GetMultiResponseTest extends BaseTest {

    @Autowired
    private WebClient client;


    @Test
    void fluxTest() {

        Flux<Response> responseFlux = this.client
                // .mutate() // this can be used to expose Webclient builder so we can change our custom properties like baseurl, default header, default cookies etc
                .get()
                .uri("reactive-math/table/{input}", 5)
                .retrieve()
                .bodyToFlux(Response.class)
                .doOnNext(System.out::println);


        StepVerifier.create(responseFlux)
                .expectNextCount(10)
                .verifyComplete();
    }


    @Test
    void fluxStreamTest() {

        Flux<Response> responseFlux = this.client
                // .mutate() // this can be used to expose Webclient builder so we can change our custom properties like baseurl, default header, default cookies etc
                .get()
                .uri("reactive-math/table/{input}/stream", 5)
                .retrieve()
                .bodyToFlux(Response.class)
                .doOnNext(System.out::println);


        StepVerifier.create(responseFlux)
                .expectNextCount(10)
                .verifyComplete();
    }
}
