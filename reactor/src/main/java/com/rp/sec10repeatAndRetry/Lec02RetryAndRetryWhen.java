package com.rp.sec10repeatAndRetry;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.util.retry.RetrySpec;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 13 Feb, 2024
 */

public class Lec02RetryAndRetryWhen {
    // triggers on Error

    private final static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {

        getIntegers()
//                .retry() // indefinitely
//                .retry(2)
//                .retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(3)))
                .retryWhen(RetrySpec.backoff(3, Duration.ofSeconds(3)))
                .subscribe(Util.subscriber());

        Util.sleepSecond(60);
    }


    private static Flux<Integer> getIntegers(){
        return Flux.range(1, 3)
                .doOnSubscribe(s -> System.out.println("Subscribed"))
                .doOnComplete(() -> System.out.println("--Completed"))
                .map(i ->  i / (Util.faker().random().nextInt(1, 5) > 3 ? 0 : 1))
                .doOnError(err -> System.out.println("--error"));
    }
}
