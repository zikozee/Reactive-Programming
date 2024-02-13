package com.rp.sec06threadingandschedulers;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 02 Feb, 2024
 */

public class Lec07FluxInterval {

    public static void main(String[] args) {
        //Flux.interval uses scheduler parallel.

        Flux.interval(Duration.ofSeconds(1))
                .subscribe(Util.subscriber());


        Util.sleepSecond(60);

    }
}
