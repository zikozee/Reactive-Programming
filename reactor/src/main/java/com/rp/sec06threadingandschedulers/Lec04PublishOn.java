package com.rp.sec06threadingandschedulers;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 02 Feb, 2024
 */

public class Lec04PublishOn {

    public static void main(String[] args) {
        // The closest subscribeOn to the publisher takes precedence
        Flux<Object> flux = Flux.create(fluxSink -> {
                    printThreadName("create");
                    for (int i = 0; i < 4; i++) {
                        fluxSink.next(i);
                        Util.sleepSecond(1);
                    }
                    fluxSink.complete();
                })
                .doOnNext(i -> printThreadName("next " + i));

        flux
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> printThreadName("next " + i))
                .publishOn(Schedulers.parallel())
                .subscribe(v -> printThreadName("sub " + v));



        Util.sleepSecond(5);
    }

    private static void printThreadName(String msg){
        System.out.println(msg + "\t\t: Thread " + Thread.currentThread().getName());
    }
}
