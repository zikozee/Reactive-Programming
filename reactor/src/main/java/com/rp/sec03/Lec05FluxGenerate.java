package com.rp.sec03;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

/**
 * @author: Ezekiel Eromosei
 * @created: 03 March 2022
 */

public class Lec05FluxGenerate {

    public static void main(String[] args) {

        // generate is only one instance and its responsible for looping itself
        // we can stop the loop by calling onComplete()
        Flux.generate(synchronousSink -> {

                    System.out.println("emitting");

            synchronousSink.next(Util.faker().country().name());

        }).take(3)
                .subscribe(Util.subscriber());

    }
}
