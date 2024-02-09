package com.ziko.webfluxdemo.webtestclient;

import com.ziko.webfluxdemo.config.RequestHandler;
import com.ziko.webfluxdemo.config.RouterConfig;
import com.ziko.webfluxdemo.dto.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 09 Feb, 2024
 */

@WebFluxTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes = {RouterConfig.class})  // to load beans not available for @WebFluxTest
public class Lec05RouterFunctionTest {

    private WebTestClient webTestClient;

//    @Autowired
//    private RouterConfig config;

    @Autowired
    private ApplicationContext context;

    @MockBean
    private RequestHandler requestHandler;

    @BeforeAll
    public void setClient() { // note i did not use static and changed default name (i.e beforeAll)

        // for single routers
//        this.webTestClient =  WebTestClient.bindToRouterFunction(config.pathRouter()).build();

        // for multiple router function -- so no need to autowire the Config Router Classes
        // just placing them in the @ContextConfiguration is enough
        this.webTestClient =  WebTestClient.bindToApplicationContext(context).build();

        // to talk to some microservice or remote server
//        this.webTestClient =  WebTestClient.bindToServer()
//                .baseUrl("http://remote-server:9090").build();
    }


    @Test
    void test() {

        Mockito.when(requestHandler.squareHandler(Mockito.any()))
                .thenReturn(
                        ServerResponse.ok().bodyValue(new Response(625))
                );

        this.webTestClient
                .get()
                // add the slash to infer in front of the URI   for webTestClient
                .uri("/router/square/{input}", 5)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Response.class)
                .value(r -> Assertions.assertThat(r.output()).isEqualTo(625));

    }
}
