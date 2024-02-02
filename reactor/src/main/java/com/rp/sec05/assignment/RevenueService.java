package com.rp.sec05.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 02 Feb, 2024
 */

public class RevenueService {

    private Map<String, Double> db = new HashMap<>();

    public RevenueService(){
        db.put("Kids", 0.0);
        db.put("Automotive", 0.0);
    }

    public Consumer<PurchaseOrder> subscribeOrderStream(){
        return p -> db.computeIfPresent(p.getCategory(), (k,v) -> v + p.getPrice());
    }

    public Flux<String> revenueStream(){
        return Flux.interval(Duration.ofSeconds(2))
                .map(i -> db.toString());
    }
}
