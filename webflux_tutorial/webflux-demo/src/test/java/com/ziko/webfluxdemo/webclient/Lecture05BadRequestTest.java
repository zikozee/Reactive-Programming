package com.ziko.webfluxdemo.webclient;

import com.ziko.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 20 Oct, 2023
 */

public class Lecture05BadRequestTest extends BaseTest {

    @Autowired
    private WebClient client;


    @Test
    void badRequestTest() {

        Mono<Response> responseMono = this.client
                // .mutate() // this can be used to expose Webclient builder so we can change our custom properties like baseurl, default header, default cookies etc
                .get()
                .uri("reactive-math/square/{input}/throw", 8)
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println)
                .doOnError(err -> System.out.println(err.getMessage()));


        StepVerifier.create(responseMono)
                .verifyError(WebClientResponseException.BadRequest.class);
    }


}
