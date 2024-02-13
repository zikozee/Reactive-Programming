package com.rp.sec10repeatAndRetry;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 13 Feb, 2024
 */

public class Lec01Repeat {

    private final static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {

        getIntegers()
//                .repeat() // indefinitely
//                .repeat(2) // i.e resubscribe to the source twice
                .repeat(() -> atomicInteger.get() < 14)  //note we are ranging from 1 - 3 [so the closet complete signals is 12, 15] Repeatedly subscribe to the source if the predicate returns true after completion of the previous subscription
//                .repeat(20, () -> true) // Repeatedly subscribe to the source if the predicate returns true after completion of the previous subscription
                .subscribe(Util.subscriber());
    }


    private static Flux<Integer> getIntegers(){
        return Flux.range(1, 3)
                .doOnSubscribe(s -> System.out.println("Subscribed"))
                .doOnComplete(() -> System.out.println("--Completed"))
                .map(i -> atomicInteger.getAndIncrement());
    }
}
