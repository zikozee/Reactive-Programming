package com.rp.sec02;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

/**
 * @author : Ezekiel Eromosei
 * @created : 26 Jan, 2022
 */

public class Lec09FluxFromToMono {

    public static void main(String[] args) {

        /*
        Mono<String> mono = Mono.just("a");

        doSomething(Flux.from(mono));

        */


        Flux.range(1, 10)
                .filter(i -> i > 3)
                .next() // to mono :: only the first item is emitted
                .subscribe(Util.onNext());

    }

    private static void doSomething(Flux<String> flux){

    }
}
