package com.rp.sec04;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05Delay {

    public static void main(String[] args) {
        // see Queues  >> XS_BUFFER_SIZE, limited to 32
        System.setProperty("reactor.bufferSize.x", "9"); // we can override

        Flux.range(1, 100)
                .log()
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Util.subscriber());

        Util.sleepSecond(60);
    }
}
