package com.rp.sec04;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec06OnError {

    public static void main(String[] args) {

        final int start = 100;
        final int end = 200;

        Flux.range(1, 10)
                .log()
                .map(i -> 10 / (5-i))
//                .onErrorReturn(-1) // examples if error occurs we can return a fixed list
//                .onErrorResume(e -> fallback(start, end)) // a microservice fails we can return data from another microservice
//                .onErrorContinue((err, o) -> {
//                    System.out.println(err.getMessage());
//                    System.out.println(o);
//                })
                .onErrorContinue(ArithmeticException.class, (err, whatCausedError) -> {
                    System.out.println(err.getMessage());
                    System.out.println(whatCausedError);
                })
                .subscribe(Util.subscriber());


    }

    private static Mono<Integer> fallback(final int start, final int end) {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(start, end));
    }
}
