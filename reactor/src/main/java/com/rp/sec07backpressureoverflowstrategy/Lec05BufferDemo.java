package com.rp.sec07backpressureoverflowstrategy;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 06 Feb, 2024
 */

public class Lec05BufferDemo {

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
        })//, FluxSink.OverflowStrategy.DROP)
//                .onBackpressureBuffer()
//                .onBackpressureBuffer(50)
                .onBackpressureBuffer(50, o -> System.out.println("Dropped: " + o))
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    Util.sleepMillis(10);
                })
                .subscribe(Util.subscriber());


        Util.sleepSecond(10);

    }
}
