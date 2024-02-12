package com.rp.sec09batching;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 12 Feb, 2024
 */

public class Lec04Window {

    // Using batching Window option makes super-sense at it emits a flux rather than constructing a list
    // since flux is a publisher it gives the event to us once it is constructed

    private static final AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {

        eventStream()
//                .window(5)// this collects the item and gives you a flux introspect next operator
//                .window(Duration.ofSeconds(2)) //same as Batching Buffer
                .windowTimeout(5, Duration.ofSeconds(2)) //same as Batching Buffer
                .flatMap(Lec04Window::saveEvents)
                .subscribe(Util.subscriber());


        Util.sleepSecond(60);
    }


    public static Flux<String> eventStream(){
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> "event"+i);
    }


    private static Mono<Integer> saveEvents(Flux<String> flux){
        return flux.doOnNext(e -> System.out.println("saving " + e))
                .doOnComplete(() -> {
                    System.out.println("saved this batch");
                    System.out.println("-----------------------");
                })
                .then(Mono.fromSupplier(atomicInteger::incrementAndGet));
    }
}
