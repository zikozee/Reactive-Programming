package com.rp.sec03;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

/**
 * @author: Ezekiel Eromosei
 * @created: 03 March 2022
 */

public class Lec01FluxCreate {


    // we handle the looping or generation ourselves
    // .create only serves what we emit
    public static void main(String[] args) {


        Flux.create(fluxSink -> {

            String country;

            do{

                country = Util.faker().country().name();
                fluxSink.next(country);

            }while (!country.equalsIgnoreCase("canada"));

            fluxSink.complete();
        }).subscribe(Util.subscriber());

    }
}
