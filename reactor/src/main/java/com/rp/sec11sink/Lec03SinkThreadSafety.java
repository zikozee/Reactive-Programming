package com.rp.sec11sink;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 14 Feb, 2024
 */

public class Lec03SinkThreadSafety {

    public static void main(String[] args) {

        // handle through which we would push items
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();

        // handle through which subscribers will receive items
        Flux<Object> flux = sink.asFlux();

        List<Object> list = new ArrayList<>();

        flux.subscribe(list::add);

        /*
        for (int i = 0; i < 1000; i++) {
            final int j = i;
            CompletableFuture.runAsync(() -> {
                sink.tryEmitNext(j); // this is not thread safe
            });
        }

        */

        for (int i = 0; i < 1000; i++) {
            final int j = i;
            CompletableFuture.runAsync(() -> {
                sink.emitNext(j,  (s, e) -> true); // this is thread safe
            });
        }

        Util.sleepSecond(3);
        System.out.println(list.size());
    }
}
