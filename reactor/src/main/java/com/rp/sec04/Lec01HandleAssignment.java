package com.rp.sec04;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;


public class Lec01HandleAssignment {

    public static void main(String[] args) {

        // handle = filter + map
        // where the first parameter is the flux type emitted in the below integer and the next param
        // is a synchronousSink (one call per invocation)

        Flux.generate(synchronousSink -> synchronousSink.next(Util.faker().country().name()))
                .map(Object::toString)
                .handle((dataString, synchronousSink) -> {
                    synchronousSink.next(dataString);

                    if(dataString.equalsIgnoreCase("Germany"))
                        synchronousSink.complete();
                }).subscribe(Util.subscriber());
    }
}
