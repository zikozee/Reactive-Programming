package com.rp.sec03;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

/**
 * @author: Ezekiel Eromosei
 * @created: 03 March 2022
 */

public class Lec07FluxGenerateCounter {

    //Generated takes an overloaded method that has as second parameter Bifunction (state, sink)
    public static void main(String[] args) {
        final int start = 1;
        Flux.generate(() -> start, (counter, sink) -> {

            String country = Util.faker().country().name();
            sink.next(country);
            if(counter >=10 || country.equalsIgnoreCase("canada"))
                sink.complete();

            return counter + 1;
        })
                .subscribe(Util.subscriber());


    }
}
