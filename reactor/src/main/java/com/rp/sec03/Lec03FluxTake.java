package com.rp.sec03;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author: Ezekiel Eromosei
 * @created: 03 March 2022
 */

public class Lec03FluxTake {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .log()
                .take(3) // cancels after long, time also
//                .take(Duration.ofMillis(1)) // cancels after long, time also
                .log()
                .subscribe(Util.subscriber());

    }
}
