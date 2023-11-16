package com.ziko.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 08 Nov, 2023
 */

@Configuration
@RequiredArgsConstructor
public class RouterConfig {

    private final RequestHandler handler;

    @Bean
    public RouterFunction<ServerResponse> pathRouter(){
        return RouterFunctions.route()
                .path("user", this::router)
                .build();
    }

    private RouterFunction<ServerResponse> router(){
        return RouterFunctions.route()
                .GET("all", handler::getAllHandler)
                .GET("{id}", handler::getByIdHandler)
                .POST(handler::insertUserHandler)
                .PUT("{id}", handler::updateUserHandler)
                .DELETE("{id}", handler::deleteUserHandler)
                .build();
    }
}
