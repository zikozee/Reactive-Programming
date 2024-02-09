package com.ziko.webfluxdemo.webtestclient;

import com.ziko.webfluxdemo.dto.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 09 Feb, 2024
 */

@SpringBootTest
@AutoConfigureWebTestClient  //add this to bring in the WebTestClient Bean
public class Lec01SimpleWebTestClientTest {

    @Autowired
    private WebTestClient webTestClient; // add the slash to infer in front of the URI  -- compare with those webclient Package to see slash diff

    @Test
    void stepVerifierTest() {

        Flux<Response> responseMono = this.webTestClient
                // .mutate() // this can be used to expose Webclient builder so we can change our custom properties like baseurl, default header, default cookies etc
                .get()
                .uri("/reactive-math/square/{number}", 5)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .returnResult(Response.class)
                .getResponseBody();


        StepVerifier.create(responseMono)
                .expectNextMatches(r -> r.output() == 25.0)
                .verifyComplete();
    }

    @Test
    void fluentAssertionTest() {

        this.webTestClient
                // .mutate() // this can be used to expose Webclient builder so we can change our custom properties like baseurl, default header, default cookies etc
                .get()
                .uri("/reactive-math/square/{number}", 5)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Response.class)
                .value(r -> Assertions.assertThat(r.output()).isEqualTo(25));

    }
}
