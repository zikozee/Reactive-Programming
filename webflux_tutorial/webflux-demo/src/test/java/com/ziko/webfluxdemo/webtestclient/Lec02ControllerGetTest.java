package com.ziko.webfluxdemo.webtestclient;

import com.ziko.webfluxdemo.controller.ParamsController;
import com.ziko.webfluxdemo.controller.ReactiveMathController;
import com.ziko.webfluxdemo.dto.Response;
import com.ziko.webfluxdemo.service.ReactiveMathService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 09 Feb, 2024
 */

@WebFluxTest(controllers = {ReactiveMathController.class, ParamsController.class})  // already contains ::-->> @AutoConfigureWebTestClient
public class Lec02ControllerGetTest {

    @Autowired
    private WebTestClient webTestClient;  // add the slash to infer in front of the URI  -- compare with those webclient Package to see slash diff

    @MockBean
    private ReactiveMathService reactiveMathService;

    @Test
    void fluentAssertionTest() {

        Mockito.when(reactiveMathService.findSquare(Mockito.anyInt())).thenReturn(Mono.just(new Response(25)));

        this.webTestClient
                .get()
                // add the slash to infer in front of the URI   for webTestClient
                .uri("/reactive-math/square/{number}", 5)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Response.class)
                .value(r -> Assertions.assertThat(r.output()).isEqualTo(25));

    }

    @Test
    void fluentAssertionEmptyTest() {

        Mockito.when(reactiveMathService.findSquare(Mockito.anyInt())).thenReturn(Mono.empty());

        this.webTestClient
                .get()
                // add the slash to infer in front of the URI   for webTestClient
                .uri("/reactive-math/square/{number}", 5)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Response.class)
                .value(r -> Assertions.assertThat(r.output()).isEqualTo(-1));

    }


    @Test
    void streamingResponseTest() {

        Mockito.when(reactiveMathService.multiplicationTable(Mockito.anyInt()))
                .thenReturn(
                        Flux.range(1, 3)
                                .map(Response::new)
                                .delayElements(Duration.ofMillis(100))
                );

        this.webTestClient
                .get()
                // add the slash to infer in front of the URI   for webTestClient
                .uri("/reactive-math/table/{input}/stream", 5)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentTypeCompatibleWith(MediaType.TEXT_EVENT_STREAM)
                .expectBodyList(Response.class)
                .hasSize(3)
                .value(r -> Assertions.assertThat(r.get(0).output()).isEqualTo(1));

    }


    @Test
    void queryParamsTest() {
        // remember to add ParamsController.class to @WebFluxTest
        Map<String, Integer> map = Map.of(
                "count", 10,
                "page", 10
        );

        this.webTestClient
                .get()
                // add the slash to infer in front of the URI   for webTestClient
                .uri(b -> b.path("/jobs/search").query("count={count}&page={page}").build(map))
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(Integer.class)
                .hasSize(2);
    }
}
