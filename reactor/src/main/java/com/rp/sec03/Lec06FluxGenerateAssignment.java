package com.rp.sec03;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Ezekiel Eromosei
 * @created: 03 March 2022
 */

public class Lec06FluxGenerateAssignment {

    public static void main(String[] args) {

        // exit if
        // canada
        // max =10
        // subscriber cancels -exit

        AtomicInteger counter = new AtomicInteger(0);

        Flux.generate(synchronousSink -> {
                    String country = Util.faker().country().name();
                    synchronousSink.next(country);
                    counter.getAndIncrement();
                    if (country.equalsIgnoreCase("canada") || counter.get() == 10)
                        synchronousSink.complete();

                })
                .subscribe(Util.subscriber());

    }
}
