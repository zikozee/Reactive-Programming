package com.ziko.webfluxdemo;

import com.ziko.webfluxdemo.dto.InputFailedValidationResponse;
import com.ziko.webfluxdemo.dto.Response;
import com.ziko.webfluxdemo.exception.InputValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 20 Oct, 2023
 */

public class Lecture06ExchangeTest extends BaseTest {

    @Autowired
    private WebClient client;

    // exchange == retrieve + additional info http status code
    @Test
    void badRequestTest() {

        Mono<Object> responseMono = this.client
                // .mutate() // this can be used to expose Webclient builder so we can change our custom properties like baseurl, default header, default cookies etc
                .get()
                .uri("reactive-math/square/{input}/throw", 8)
                .exchangeToMono(this::exchange)
                .doOnNext(System.out::println)
                .doOnError(err -> System.out.println(err.getMessage()));


        StepVerifier.create(responseMono)
                .expectNextCount(1)
                .verifyComplete();
    }

    private Mono<Object> exchange(ClientResponse cr){
        if(cr.statusCode() == HttpStatusCode.valueOf(400)){
            return cr.bodyToMono(InputFailedValidationResponse.class);
        }else
            return cr.bodyToMono(Response.class);
    }


}
