package com.rp.sec10repeatAndRetry;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;
import reactor.util.retry.RetrySpec;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 13 Feb, 2024
 */

public class Lec02RetrySpecAdvanced {
    // triggers on Error

    private final static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        // we are using the RetrySpec.from(...)
        // which takes in what we intend to use as retry signal and returns a publisher
        // in this use case, if error is 500 keep retry else if 400 pass err to the downstream

        orderService(Util.faker().business().creditCardNumber())
                .retryWhen(RetrySpec.from(
                        flux -> flux
                                .doOnNext(rs -> {
                                    System.out.println(rs.totalRetries());
                                    System.out.println(rs.failure());
                                })
                                .handle((rs, synchronousSink) -> {
                                    if(rs.failure().getMessage().equals("500"))
                                        synchronousSink.next(1);
                                    else
                                        synchronousSink.error(rs.failure());
                                })
                                .delayElements(Duration.ofSeconds(1))
                ))
                .subscribe(Util.subscriber());

        Util.sleepSecond(60);

    }




    // order service
    private static Mono<String> orderService(String ccNumber){
        return Mono.fromSupplier(() -> {
            processPayment(ccNumber);
            return Util.faker().idNumber().valid();
        });
    }

    //payment service
    private static void processPayment(String ccNumber){
        int random = Util.faker().random().nextInt(1, 10);

        if(random < 8)
            throw new RuntimeException("500");
        else if (random < 10)
            throw new RuntimeException("404");
    }
}
