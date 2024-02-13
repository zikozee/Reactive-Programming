package com.rp.sec08combiningpublishers;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 08 Feb, 2024
 */

public class Assignment {

    public static void main(String[] args) {

//        Flux.range(1, 12)
//                .map(i -> (10000 - (i*100)) * ThreadLocalRandom.current().nextDouble(0.8, 1.3))
//                .subscribe(Util.subscriber());


//        Flux.range(1, 12)
//                .map(i -> i * 100)
//                .map(j -> (10000 - j))
//                .map(k -> {
//                    double factor = ThreadLocalRandom.current().nextDouble(0.8, 1.3);
//                    System.out.println("factor: " + factor);
//                    return k * factor;
//                })
//                .subscribe(Util.subscriber());


        final int carPrice = 10000;

        Flux.combineLatest(monthStream(), demandStreamFactor(), (month, demand) -> {
                    System.out.println("Demand: " + demand + ", month:" + month);
            return (carPrice - (month * 100)) * demand;
        })
                .subscribe(Util.subscriber());

        Util.sleepSecond(20);
    }

    private static Flux<Double> demandStreamFactor(){
        return Flux.interval(Duration.ofSeconds(3))
                .map(i -> Util.faker().random().nextInt(80, 120) / 100d)
                .startWith(1d);
    }

    private static Flux<Long> monthStream(){
        return Flux.interval(Duration.ZERO, Duration.ofSeconds(1));
    }
}
