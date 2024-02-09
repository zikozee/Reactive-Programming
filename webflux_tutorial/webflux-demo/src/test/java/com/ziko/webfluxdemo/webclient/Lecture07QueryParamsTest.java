package com.ziko.webfluxdemo.webclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.net.URI;
import java.util.Map;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 20 Oct, 2023
 */

public class Lecture07QueryParamsTest extends BaseTest {

    @Autowired
    private WebClient client;

    public static final String QUERY_STRING = "http://localhost:8080/jobs/search?count={count}&page={page}";
    // exchange == retrieve + additional info http status code
    @Test
    void queryParamsTest() {
        URI uri = UriComponentsBuilder.fromUriString(QUERY_STRING)
                .build(10, 20);

        Flux<Integer> flux = this.client
                // .mutate() // this can be used to expose Webclient builder so we can change our custom properties like baseurl, default header, default cookies etc
                .get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(Integer.class)
                .doOnNext(System.out::println);


        StepVerifier.create(flux)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void queryParamsTest2() {// considering we already have a base
        Map<String, Integer> map = Map.of(
                "count", 10,
                "page", 10
        );

        Flux<Integer> flux = this.client
                // .mutate() // this can be used to expose Webclient builder so we can change our custom properties like baseurl, default header, default cookies etc
                .get()
                .uri(b -> b.path("jobs/search").query("count={count}&page={page}").build(map))
//                .uri(b -> b.path("jobs/search").query("count={count}&page={page}").build(10, 20))
//                .uri(b -> b.path("jobs/search")
//                        .queryParam("count", 10)
//                        .queryParam("page", 20)
//                        .build()
//                )
                .retrieve()
                .bodyToFlux(Integer.class)
                .doOnNext(System.out::println);


        StepVerifier.create(flux)
                .expectNextCount(2)
                .verifyComplete();
    }

}
