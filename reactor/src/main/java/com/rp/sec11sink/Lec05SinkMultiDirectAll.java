package com.rp.sec11sink;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.util.concurrent.Queues;

import java.time.Duration;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 14 Feb, 2024
 */

public class Lec05SinkMultiDirectAll {

    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");

        // handle through which we would push items
//        Sinks.Many<Object> sink = Sinks.many().multicast().directAllOrNothing();  // Either everybody gets the value or nobody gets it
        Sinks.Many<Object> sink = Sinks.many().multicast().directBestEffort();  // favors fast subscribers and ignores slow subscribers

        // handle through which subscribers will receive items
        Flux<Object> flux = sink.asFlux();



        flux.subscribe(Util.subscriber("sam"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike"));

        for (int i = 0; i < 100; i++) {
            sink.tryEmitNext(i);
        }

        Util.sleepSecond(10);
    }
}
