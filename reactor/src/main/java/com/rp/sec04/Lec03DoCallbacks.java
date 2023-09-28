package com.rp.sec04;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec03DoCallbacks {

    public static void main(String[] args) {

        Flux.create(fluxSink -> {
                System.out.println("inside create");
            for (int i = 0; i < 5; i++) {
                fluxSink.next(i);
            }

//            fluxSink.error(new RuntimeException("oops"));
//            fluxSink.complete();
            System.out.println("--completed");
        }) // publisher
                .doOnComplete(() -> System.out.println("doOnComplete"))
                .doFirst(() -> System.out.println("doFirst 1"))
                .doOnNext(o -> System.out.println("doOnNext: " + o))
                .doOnSubscribe(s -> System.out.println("doOnSubscribe 1 " + s))
                .doOnRequest(l -> System.out.println("doOnRequest " + l))
                .doFirst(() -> System.out.println("doFirst 2"))
                .doOnError(err -> System.out.println("doOnError " + err.getMessage()))
                .doOnTerminate(() -> System.out.println("doOnTerminate"))
                .doOnCancel(() -> System.out.println("doOnCancel"))
                .doOnSubscribe(s -> System.out.println("doOnSubscribe  2 " + s))
                .doFinally(signal -> System.out.println("doFinally 1: " + signal))
                .doFirst(() -> System.out.println("doFirst 3"))
                .doOnDiscard(Object.class, o -> System.out.println("doOnDiscard " + o))
                .take(2)
                .doFinally(signal -> System.out.println("doFinally 2: " + signal))
                .subscribe(Util.subscriber()); // subscriber

    }

    // doFirst goes in order of subscriber to publisher,,, forst to br executed
    // doOnSubscribe ::subscription object goes from publisher to subscriber
    // after which doOnRequest comes In
    // then create

    // if an error is thrown, doOnError kicks in
    // if you exit on Time say you take(2), doOnCancel kicks in

    // where you place doFinally really matters
    //  take will trigger the cancel
}
