package com.rp.sec03;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

/**
 * @author: Ezekiel Eromosei
 * @created: 03 March 2022
 */

public class Lec04FluxCreateIssueFix {

    public static void main(String[] args) {

        Flux.create(fluxSink -> {

            String country;

            do{

                country = Util.faker().country().name();
                System.out.println("Emitting : " + country);
                fluxSink.next(country);

            }while (!country.equalsIgnoreCase("canada") && !fluxSink.isCancelled());

            fluxSink.complete();
        })
                .take(3)
                .subscribe(Util.subscriber());
    }
}
