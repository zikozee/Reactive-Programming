package com.ziko.webfluxdemo.service;

import com.ziko.webfluxdemo.dto.MultiplyRequestDto;
import com.ziko.webfluxdemo.dto.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

@Service
public class ReactiveMathService {

    public Mono<Response> findSquare(int input) {
        return Mono.fromSupplier(() -> input *  input)
                .map(Response::new);
    }

    public Flux<Response> multiplicationTable(int input) {
        return Flux.range(1, 10)
//                .doOnNext(i -> SleepUtil.sleepSeconds(1))
                .delayElements(Duration.ofSeconds(1)) // non-blocking sleep
                .doOnNext(i -> System.out.println("reactive-math-service processing: " + i))
                .map(i -> new Response(i * input));
    }

    public Mono<Response> multiply(Mono<MultiplyRequestDto> dtoMono) {
        return dtoMono
//                .map(dto -> dto.first() * dto.second())
//                .flatMap(dto -> Mono.fromSupplier(() -> dto.first() * dto.second()))
                .flatMap(dto -> Mono.fromSupplier(() -> new Response(dto.first() * dto.second())));


    }

    public Function<MultiplyRequestDto, Integer> transform(){ // already infers i am passing a type T (MultiplyRequestDto) and returning a Type R (Integer)
        return (a) -> a.first() * a.second();
    }

}
