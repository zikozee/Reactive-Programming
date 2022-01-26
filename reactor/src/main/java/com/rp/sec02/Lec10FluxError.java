package com.rp.sec02;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

/**
 * @author : Ezekiel Eromosei
 * @created : 26 Jan, 2022
 */

public class Lec10FluxError {

    public static void main(String[] args) {

        Flux.error(new RuntimeException("just an error"))
                .subscribe(Util.onNext(), Util.onError());
    }
}
