package com.rp.sec08combiningpublishers.helper;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 08 Feb, 2024
 */

public class QatarFlights {

    public static Flux<String> getFlights(){
        return Flux.range(1, Util.faker().random().nextInt(1, 5))
                .delayElements(Duration.ofSeconds(1))
                .map(i -> "Qatar " + Util.faker().random().nextInt(100, 999))
                .filter(i -> Util.faker().random().nextBoolean());
    }
}
