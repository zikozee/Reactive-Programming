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

public class InventoryService {

    private final Map<String, Integer> db = new HashMap<>();

    public InventoryService(){
        db.put("Kids", 100);
        db.put("Automotive", 100);
    }

    public Consumer<PurchaseOrder> subscribeOrderStream(){
        return p -> db.computeIfPresent(p.getCategory(), (k,v) -> v - p.getQuantity());
    }

    public Flux<String> inventoryStream(){
        return Flux.interval(Duration.ofSeconds(2))
                .map(i -> db.toString());
    }
}
