package com.rp.sec02;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @author : Ezekiel Eromosei
 * @created : 26 Jan, 2022
 */

public class Lec03FluxFromArrayOrList {

    public static void main(String[] args) {

        List<String> strings  = List.of("a", "b", "c");

        Flux.fromIterable(strings)
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

        Integer[] integers = {2,4,5,6,6};

        Flux.fromArray(integers)
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }
}
