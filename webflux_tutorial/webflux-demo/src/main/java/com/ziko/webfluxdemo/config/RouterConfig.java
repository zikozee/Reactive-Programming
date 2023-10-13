package com.ziko.webfluxdemo.config;

import com.ziko.webfluxdemo.dto.InputFailedValidationResponse;
import com.ziko.webfluxdemo.exception.InputValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

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
