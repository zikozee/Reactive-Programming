package com.rp.sec09batching;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 12 Feb, 2024
 */

public class Lec01Buffer {
    public static void main(String[] args) {

        eventStream()
//                .buffer(5) // this collects the item and gives you a list introspect next operator
//                .buffer(Duration.ofSeconds(2))
                .bufferTimeout(5, Duration.ofSeconds(2))// whichever comes first :-> 5 items or 2 seconds mark reached
                .subscribe(Util.subscriber());


        Util.sleepSecond(60);
    }


    public static Flux<String> eventStream(){
        return Flux.interval(Duration.ofMillis(300))
//                .take(3) // if the event available is lesser than buffer, it takes what's available and completes
                .map(i -> "event"+i);
    }
}
