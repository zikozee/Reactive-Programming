package com.rp.sec04;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;


public class Lec01Handle {

    public static void main(String[] args) {

        // handle = filter + map
        // where the first parameter is the flux type emitted in the below integer and the next param
        // is a synchronousSink (one call per invocation)

        Flux.range(1, 20)
                .handle((integer, synchronousSink) -> {
                    if(integer == 7)
                        synchronousSink.complete();
                    else
                        synchronousSink.next(integer);
                })
                .subscribe(Util.subscriber());
    }
}
