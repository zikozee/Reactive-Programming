package com.ziko.webfluxdemo.webtestclient;


import com.ziko.webfluxdemo.controller.ReactiveMathController;
import com.ziko.webfluxdemo.dto.MultiplyRequestDto;
import com.ziko.webfluxdemo.dto.Response;
import com.ziko.webfluxdemo.service.ReactiveMathService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 09 Feb, 2024
 */

@WebFluxTest(controllers = {ReactiveMathController.class})  // already contains ::-->> @AutoConfigureWebTestClient
public class Lec03PostTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ReactiveMathService reactiveMathService;

    @Test
    void postTest() {

        Mockito.when(reactiveMathService.multiply(Mockito.any()))
                .thenReturn(
                        Mono.just(new Response(15))
                );


        this.webTestClient
                .post()
                // add the slash to infer in front of the URI   for webTestClient
                .uri("/reactive-math/multiply")
                .accept(MediaType.APPLICATION_JSON)
                .headers(h -> h.setBasicAuth("username", "password"))
                .headers(h -> h.set("someKey", "someValue"))
                .bodyValue(new MultiplyRequestDto(3, 5))
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Response.class)
                .value(r -> Assertions.assertThat(r.output()).isEqualTo(15));

    }
}
