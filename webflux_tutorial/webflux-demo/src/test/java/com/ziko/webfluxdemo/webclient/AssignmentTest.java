package com.ziko.webfluxdemo.webclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 20 Oct, 2023
 */

public class AssignmentTest extends BaseTest {

    public static final int A = 10;
    public static final String FORMAT = "%d %s %d = %s";

    @Autowired
    private WebClient client;

    @Test
    void assignment() {

        Flux<String> stringFlux = Flux.range(1, 5)
                .flatMap(b -> Flux.just("+", "-", "*", "/")
                        .flatMap(op -> send(b, op)))
                .doOnNext(System.out::println);


        StepVerifier.create(stringFlux)
                .expectNextCount(20)
                .verifyComplete();
    }

    private Mono<String> send(int b, String op) {
        return this.client
                .get()
                .uri("calculator/{a}/{b}", A, b)
                .headers(h -> h.set("OP", op))
                .retrieve()
                .bodyToMono(Integer.class)
//                .doOnNext(System.out::println)
                .map(v -> String.format(FORMAT, A, op, b, v));
    }
}
