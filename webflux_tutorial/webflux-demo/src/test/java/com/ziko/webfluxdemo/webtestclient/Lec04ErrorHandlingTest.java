package com.ziko.webfluxdemo.webtestclient;

import com.ziko.webfluxdemo.controller.ReactiveMathController;
import com.ziko.webfluxdemo.controller.ReactiveMathValidationController;
import com.ziko.webfluxdemo.dto.InputFailedValidationResponse;
import com.ziko.webfluxdemo.dto.MultiplyRequestDto;
import com.ziko.webfluxdemo.dto.Response;
import com.ziko.webfluxdemo.exception.InputValidationException;
import com.ziko.webfluxdemo.service.ReactiveMathService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 09 Feb, 2024
 */

@WebFluxTest(controllers = {ReactiveMathValidationController.class})
public class Lec04ErrorHandlingTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ReactiveMathService reactiveMathService;

    @Test
    void postTest() {

        Mockito.when(reactiveMathService.findSquare(Mockito.anyInt()))
                .thenReturn(Mono.just(new Response(3)));
//                .thenThrow(InputValidationException.class);


        this.webTestClient
                .get()
                // add the slash to infer in front of the URI   for webTestClient
                .uri("/reactive-math/square/{input}/throw", 3)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.errorCode").exists()
                .jsonPath("$.message").isEqualTo("allowed range is 10-20")
                .jsonPath("$.input").isNumber();
//                .expectBody(InputFailedValidationResponse.class)
//                .value(v -> Assertions.assertThat(v.errorCode()).isEqualTo(100));

    }
}
