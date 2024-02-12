package com.rp.sec09batching;

import com.rp.courseutil.Util;
import com.rp.sec09batching.assignment.OrderProcessor;
import com.rp.sec09batching.assignment.OrderService;
import com.rp.sec09batching.assignment.PurchaseOrder;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 12 Feb, 2024
 */

public class Lec06Assignment {

    public static void main(String[] args) {

        Map<String, Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>>> map = Map.of(
                "Kids", OrderProcessor.kidsProcessing(),
                "Automotive", OrderProcessor.automotiveProcessing()
        );

        Set<String> set = map.keySet();

        OrderService.orderStream()
                .filter(p -> set.contains(p.getCategory()))
                .groupBy(PurchaseOrder::getCategory) // 2 Keys   --->>> the below gives a Flux<GroupedFlux<String, PurchaseOrder> and GroupedFlux  extends a Flux so, it's a flux
                .flatMap(gf -> map.get(gf.key()).apply(gf)) //flux
                .subscribe(Util.subscriber());


        Util.sleepSecond(60);

    }
}
