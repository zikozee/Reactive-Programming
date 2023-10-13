package com.ziko.webfluxdemo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
@RequiredArgsConstructor
public class CalculatorRouterConfig {

    private final AssignmentCalculatorHandler handler;


    @Bean
    public RouterFunction<ServerResponse> assignmentRouter() {
        return RouterFunctions.route()
                .path("calculator", this::router3)
                .build();
    }

    private RouterFunction<ServerResponse> router3() {
        return RouterFunctions.route()
                .GET("{first}/{second}", isOperation("+"), handler::additionHandler)
                .GET("{first}/{second}", isOperation("-"), handler::subtractionHandler)
                .GET("{first}/{second}", isOperation("*"), handler::multiplicationHandler)
                .GET("{first}/{second}", isOperation("/"), handler::divisionHandler)
                .GET("{first}/{second}", request -> ServerResponse.badRequest().bodyValue("OP should be + - * /"))
                .build();
    }

    private static RequestPredicate isOperation(String operation) {
        return RequestPredicates.headers(headers -> operation.equals(
                headers.asHttpHeaders().toSingleValueMap()
                        .get("OP")));
    }
}
