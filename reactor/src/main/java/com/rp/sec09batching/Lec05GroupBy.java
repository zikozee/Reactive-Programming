package com.rp.sec09batching;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 12 Feb, 2024
 */

public class Lec05GroupBy {


    public static void main(String[] args) {


        Flux.range(1, 30)
                .delayElements(Duration.ofSeconds(1)) // the below gives a Flux<GroupedFlux<K, T>> and GroupedFlux  extends a Flux so it's a flux
                .groupBy(i -> i % 2)
                .subscribe(gf -> process(gf, gf.key()));

        Util.sleepSecond(60);
    }


    private static void process(Flux<Integer> flux, int key){
        System.out.println("Called");
        flux.subscribe(i -> System.out.println("Key: " + key + ", Item: " + i));
    }
}
