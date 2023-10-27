package com.ziko.webfluxdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 20 Oct, 2023
 */

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(){

        return WebClient.builder()
                .baseUrl("http://localhost:8080") // optional
//                .defaultHeaders(h -> h.setBasicAuth("username, password"))
                .filter(this::sessionToken)
                .build();
    }


    // in case we need to globally set an auth token to validate all request
//    private Mono<ClientResponse> sessionToken(ClientRequest request, ExchangeFunction ex){
//        System.out.println("generating session token");
//        ClientRequest clientRequest = ClientRequest.from(request)
//                .headers(h -> h.setBearerAuth("some-lengthy-jwt")).build();
//
//        return ex.exchange(clientRequest);
//    }

    private Mono<ClientResponse> sessionToken(ClientRequest request, ExchangeFunction ex){
        // auth --> basic or auth
        ClientRequest clientRequest = request.attribute("auth")
                .map(v -> v.equals("basic") ? withBasicAuth(request) : withOAuth(request))
                .orElse(request);

        return ex.exchange(clientRequest);
    }

    // setting different kinds of credentials at runtime
    // basically dynamically changing some request header or data based on some provided attribute
    private ClientRequest withBasicAuth(ClientRequest request){
        return ClientRequest.from(request)
                .headers(h -> h.setBasicAuth("username", "password"))
                .build();
    }

    private ClientRequest withOAuth(ClientRequest request){
        return ClientRequest.from(request)
                .headers(h -> h.setBearerAuth("some-token"))
                .build();
    }
}
