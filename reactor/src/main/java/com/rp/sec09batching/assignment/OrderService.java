package com.rp.sec09batching.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 12 Feb, 2024
 */

public class OrderService {

    public static Flux<PurchaseOrder> orderStream(){
        return Flux.interval(Duration.ofMillis(100))
                .map(i -> new PurchaseOrder());
    }
}
