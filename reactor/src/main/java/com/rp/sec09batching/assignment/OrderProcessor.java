package com.rp.sec09batching.assignment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 12 Feb, 2024
 */

public class OrderProcessor {

    public static Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>> automotiveProcessing(){
        return flux -> flux
                .doOnNext(p -> p.setPrice(0.1 * p.getPrice()))
                .doOnNext(p -> p.setItem("{{" + p.getItem() +"}}"));
    }

    public static Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>> kidsProcessing(){
        return flux -> flux
                .doOnNext(p -> p.setPrice(0.5 * p.getPrice()))
                .flatMap(p -> Flux.concat(Mono.just(p), getFreeKidsOrder()));
    }


    private static Mono<PurchaseOrder> getFreeKidsOrder(){
        return Mono.fromSupplier(() -> {
            PurchaseOrder po = new PurchaseOrder();
            po.setItem("FREE "  + po.getItem());
            po.setPrice(0);
            po.setCategory("Kids");

            return po;
        });
    }
}
