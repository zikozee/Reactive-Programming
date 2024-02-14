package com.rp.sec11sink;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 14 Feb, 2024
 */

public class Lec06SinkReplay {

    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");

        // handle through which we would push items

//        Sinks.Many<Object> sink = Sinks.many().replay().all(); // cache for all. more like replay previous history
        Sinks.Many<Object> sink = Sinks.many().replay().all(2);


        // handle through which subscribers will receive items
        Flux<Object> flux = sink.asFlux();


        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike")); // mike will also get a value

        sink.tryEmitNext("?");

        flux.subscribe(Util.subscriber("jake")); // mike will also get a value

        sink.tryEmitNext("new msg");
    }
}
