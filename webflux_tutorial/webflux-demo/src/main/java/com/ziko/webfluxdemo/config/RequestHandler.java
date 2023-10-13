package com.ziko.webfluxdemo.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ziko.webfluxdemo.dto.MultiplyRequestDto;
import com.ziko.webfluxdemo.dto.Response;
import com.ziko.webfluxdemo.exception.InputValidationException;
import com.ziko.webfluxdemo.service.ReactiveMathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
public class RequestHandler {

    private final ReactiveMathService reactiveMathService;

    public Mono<ServerResponse> squareHandler(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));

        Mono<Response> square = this.reactiveMathService.findSquare(input); // builds the pipeline

        return ServerResponse.ok().body(square, Response.class);
    }

    public Mono<ServerResponse> multiplicationTableHandler(ServerRequest serverRequest){

        int input = Integer.parseInt(serverRequest.pathVariable("input"));

        Flux<Response>  multiplicationTable = this.reactiveMathService.multiplicationTable(input);

        return ServerResponse.ok()
                .body(multiplicationTable, Response.class);
    }


    public Mono<ServerResponse> multiplicationStreamHandler(ServerRequest serverRequest){

        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Flux<Response>  multiplicationTable = this.reactiveMathService.multiplicationTable(input);
        return ServerResponse.ok()
//                .header("Content-Type", MediaType.TEXT_EVENT_STREAM_VALUE)
//                .headers(httpHeaders -> {
//                    httpHeaders.setContentType(MediaType.TEXT_EVENT_STREAM);
//                })
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(multiplicationTable, Response.class);

    }


    public Mono<ServerResponse> multiplyHandler(ServerRequest serverRequest){
        Mono<MultiplyRequestDto> multiplyRequestDtoMono = serverRequest.bodyToMono(MultiplyRequestDto.class);

        System.out.println(serverRequest.headers());

        Mono<Response>  multiplicationTable = this.reactiveMathService.multiply(multiplyRequestDtoMono);
        return ServerResponse.ok()
                .body(multiplicationTable, Response.class);
    }


    //FOR ERROR HANDLING SCENARIO
    public Mono<ServerResponse> squareHandlerValidation(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        if(input <10 || input > 20){
            return Mono.error(new InputValidationException(input));
        }

        Mono<Response> square = this.reactiveMathService.findSquare(input); // builds the pipeline
        return ServerResponse.ok().body(square, Response.class);
    }



}
