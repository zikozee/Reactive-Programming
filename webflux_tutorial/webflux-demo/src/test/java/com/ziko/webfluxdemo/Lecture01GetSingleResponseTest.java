package com.ziko.webfluxdemo;

import com.ziko.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 20 Oct, 2023
 */

public class Lecture01GetSingleResponseTest extends BaseTest{

    @Autowired
    private WebClient client;

    @Test
    void blockTest() {

        Response response = this.client
                // .mutate() // this can be used to expose Webclient builder so we can change our custom properties like baseurl, default header, default cookies etc
                .get()
                .uri("reactive-math/square/{number}", 5)
                .retrieve()
                .bodyToMono(Response.class)
                .block();

        System.out.println(response);
        assertThat(response).isNotNull();
        assertThat(response.output()).isEqualTo(25.0);
    }

    @Test
    void stepVerifierTest() {

        Mono<Response> responseMono = this.client
                // .mutate() // this can be used to expose Webclient builder so we can change our custom properties like baseurl, default header, default cookies etc
                .get()
                .uri("reactive-math/square/{number}", 5)
                .retrieve()
                .bodyToMono(Response.class);


        StepVerifier.create(responseMono)
                .expectNextMatches(r -> r.output() == 25.0)
                .verifyComplete();
    }

}
