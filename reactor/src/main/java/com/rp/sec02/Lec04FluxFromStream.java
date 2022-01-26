package com.rp.sec02;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author : Ezekiel Eromosei
 * @created : 26 Jan, 2022
 */


//todo you can't reuse streams
// after usage it closes
public class Lec04FluxFromStream {

    public static void main(String[] args) {

        Stream<Integer> stream = Stream.of(1,2,3,4,5,6);

        Flux<Integer> integerFlux= Flux.fromStream(stream);

        integerFlux.subscribe(Util.onNext(), Util.onError(), Util.onComplete());
        //stream has closed hence can't call .subscribe() again on it

        integerFlux.subscribe(Util.onNext(), Util.onError(), Util.onComplete());

        /*
        * Better way use supplier stream
        */

        Flux<Integer> integerFlux2= Flux.fromStream(() -> Stream.of(1,2,3,4,5,6));

        integerFlux2.subscribe(Util.onNext(), Util.onError(), Util.onComplete());
        integerFlux2.subscribe(Util.onNext(), Util.onError(), Util.onComplete());



    }
}
