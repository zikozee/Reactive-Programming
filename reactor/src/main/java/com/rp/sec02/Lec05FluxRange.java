package com.rp.sec02;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

/**
 * @author : Ezekiel Eromosei
 * @created : 26 Jan, 2022
 */

public class Lec05FluxRange {

    public static void main(String[] args) {
        Flux.range(3, 10)
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

        Flux.range(1, 10)
                .log()
                .map(i -> Util.faker().name().fullName())
                .log()
                .subscribe(Util.onNext());
    }
}
