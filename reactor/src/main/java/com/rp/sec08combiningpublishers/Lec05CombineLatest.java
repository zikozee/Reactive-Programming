package com.rp.sec08combiningpublishers;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 08 Feb, 2024
 */

public class Lec05CombineLatest {

    public static void main(String[] args) {

        // A will be missing, because when getNumber emitted 1, B was the latest
        Flux.combineLatest(getString(), getNumber(), (a, b) -> a + b)
                .subscribe(Util.subscriber());

        Util.sleepSecond(10);
    }

    private static Flux<String> getString(){
         return Flux.just("A", "B", "C", "D")
                 .delayElements(Duration.ofSeconds(1));
    }

    private static Flux<Integer> getNumber(){
        return Flux.just(1,2,3)
                .delayElements(Duration.ofSeconds(3));
    }
}
