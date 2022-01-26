package com.rp.sec02;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

/**
 * @author : Ezekiel Eromosei
 * @created : 26 Jan, 2022
 */

public class Lec01FluxIntro {

    public static void main(String[] args) {

        Flux<Object> flux = Flux.just(1,2,3,4, "a", Util.faker().internet().emailAddress());

        flux.subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }
}
