package com.ziko.webfluxdemo.config;

import com.ziko.webfluxdemo.dto.InputFailedValidationResponse;
import com.ziko.webfluxdemo.exception.InputValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Configuration
@RequiredArgsConstructor
public class RouterConfig {

    // matches the reactive-math-Controller
    private final RequestHandler handler;

    //router2
    @Bean
    public RouterFunction<ServerResponse> pathRouter(){
        return RouterFunctions.route()
                .path("router1", this::router2)
                // this will check if the router begins with router1, and the maps looks for the
                // uri in the router
                // example http://localhost:8080/router1/square/8
                // it sees that router1 is present then it checks which uri matches **square/8**
                .build();
    }

    private RouterFunction<ServerResponse> router2(){
        return RouterFunctions.route()
                // this would only match 10 -20, i.e start with 1 and accept another digit
                .GET("square/{input}", RequestPredicates.path("*/1?").or(RequestPredicates.path("*/20")), handler::squareHandler)
                .GET("square/{input}", req -> ServerResponse.badRequest().bodyValue("only 10-19 allowed"))
                .GET("table/{input}", handler::multiplicationTableHandler)
                .GET("table/{input}/stream", handler::multiplicationStreamHandler)
                .POST("multiply", handler::multiplyHandler)
                .GET("square-validation/{input}", handler::squareHandlerValidation)
                .onError(InputValidationException.class, exceptionHandler())
                .build();
    }


//router1
    @Bean
    public RouterFunction<ServerResponse> serverResponseRouterFunction(){
        return RouterFunctions.route()
                .GET("router/square/{input}", handler::squareHandler)
                .GET("router/table/{input}", handler::multiplicationTableHandler)
                .GET("router/table/{input}/stream", handler::multiplicationStreamHandler)
                .POST("router/multiply", handler::multiplyHandler)
                .GET("router/square-validation/{input}", handler::squareHandlerValidation)
                .onError(InputValidationException.class, exceptionHandler())
                .build();
    }

    private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> exceptionHandler(){
        return (error, req) -> {
            InputValidationException ex = (InputValidationException)error;
            InputFailedValidationResponse response =
                    new InputFailedValidationResponse(ex.getErrorCode(), ex.getInput(), ex.getMessage());

            return ServerResponse.badRequest().bodyValue(response);
        };
    }

    //todo
    // I use Hibernate ValidationFactory or the Custom Validation Class,
    // Use an ApiBaseResponse
    // call the CustomValidator class to receive the request input and/or body in the RequestHandler
    // check the exception Type
    // build the ApiBaseResponse and return as ServerResponse.badRequest.bodyValue(apiBaseResponse);
}
