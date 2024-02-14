package com.rp.sec11sink;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 14 Feb, 2024
 */

public class Lec04SinkMulti {

    public static void main(String[] args) {

        // handle through which we would push items
        Sinks.Many<Object> sink = Sinks.many().multicast().onBackpressureBuffer();
//        Sinks.Many<Object> sink = Sinks.many().multicast().directAllOrNothing();  // ***

        // handle through which subscribers will receive items
        Flux<Object> flux = sink.asFlux();


        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike")); // mike will also get a value

        sink.tryEmitNext("?");

        flux.subscribe(Util.subscriber("jake")); // mike will also get a value

        sink.tryEmitNext("new msg");

       // not mike will only get to see messages that are published after he subscribed
        // when there are no subscribers we store items in the queue. THIS IS DEFAULT
                // or er can disable history see *** above
        // hence sam will get all the values
    }
}
