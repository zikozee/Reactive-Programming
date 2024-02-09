package com.ziko.webfluxdemo.webclient;

import com.ziko.webfluxdemo.dto.MultiplyRequestDto;
import com.ziko.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 20 Oct, 2023
 */

public class Lecture08AttributesTest extends BaseTest {

    @Autowired
    private WebClient client;

    @Test
    void headerTest() {
        // see WebClientConfig in config package in test
        // to influence the behavior of the configuration

        Mono<Response> responseFlux = this.client
                // .mutate() // this can be used to expose Webclient builder, so we can change our custom properties like baseurl, default header, default cookies etc
                .post()
                .uri("reactive-math/multiply")
                .bodyValue(new MultiplyRequestDto(5, 10))
//                .attribute("auth", "basic")
                .attribute("auth", "OAuth")
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println);


        StepVerifier.create(responseFlux)
                .expectNextCount(1)
                .verifyComplete();
    }


}
