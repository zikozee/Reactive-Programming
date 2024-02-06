package com.rp.sec07;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 06 Feb, 2024
 */

public class Lec04ErrorDemo {

    public static void main(String[] args) {
        // irregular values, 75% will be drained i.e 12  the next item will be pushed into the buffer
        // monitor pushed number after it Receives 12
        System.setProperty("reactor.bufferSize.small", "16"); // without this we get the default set which is 256

        Flux.create(fluxSink -> {
            for (int i = 1; i < 201 && !fluxSink.isCancelled(); i++) {
                fluxSink.next(i);
                System.out.println("Pushed : " + i);
                Util.sleepMillis(1);
            }
            fluxSink.complete();
        })
                .onBackpressureError()
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    Util.sleepMillis(10);
                })
                .subscribe(Util.subscriber());


        Util.sleepSecond(10);

    }
}
