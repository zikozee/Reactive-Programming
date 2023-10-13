package com.ziko.webfluxdemo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class RouterConfig {

    // matches the reactive-math-Controller
    private final RequestHandler handler;

    @Bean
    public RouterFunction<ServerResponse> serverResponseRouterFunction(){
        return RouterFunctions.route()
                .GET("router/square/{input}", handler::squareHandler)
                .GET("router/table/{input}", handler::multiplicationTableHandler)
                .GET("router/table/{input}/stream", handler::multiplicationStreamHandler)
                .POST("router/multiply", handler::multiplyHandler)
                .build();
    }
}
