package com.rp.sec04;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec04LimitRate {

    public static void main(String[] args) {
        Flux.range(1, 1000)
                .log()
//                .limitRate(100)
                .limitRate(100, 45)
                .subscribe(Util.subscriber());
    }// limitRate only emit items only if 75% is drained then it request more data from the publisher
    // can be controlled by the overloaded method
    // highTide -> request this
    // lowTide -> amount to be consumed before requesting (highTide amount)
    // (100, 100) if values are same it behaves as 75%
    // (100, 0) it consumes total highTide before requesting (highTide amount)
}
