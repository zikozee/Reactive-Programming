package com.ziko.webfluxdemo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;


@Component
@RequiredArgsConstructor
public class AssignmentCalculatorHandler {


    public Mono<ServerResponse> additionHandler(ServerRequest serverRequest){
        return process(serverRequest, (a, b) -> ServerResponse.ok().bodyValue(a + b));
    }

    public Mono<ServerResponse> subtractionHandler(ServerRequest serverRequest){
        return process(serverRequest, (a, b) -> ServerResponse.ok().bodyValue(a - b));
    }

    public Mono<ServerResponse> multiplicationHandler(ServerRequest serverRequest){
        return process(serverRequest, (a, b) -> ServerResponse.ok().bodyValue(a * b));
    }

    public Mono<ServerResponse> divisionHandler(ServerRequest serverRequest){
        return process(serverRequest, (a, b) ->
                b != 0 ?  ServerResponse.ok().bodyValue(a /(double)b)
                        : ServerResponse.ok().bodyValue("second cannot be zero"));
    }

    private Mono<ServerResponse> process(ServerRequest request,
                                         BiFunction<Integer, Integer, Mono<ServerResponse>> operationLogic) {

        int first = getValue(request, "first");
        int second = getValue(request, "second");

        return operationLogic.apply(first, second);
    }

    private int getValue(ServerRequest request, String key){
        return Integer.parseInt(request.pathVariable(key));
    }
}
