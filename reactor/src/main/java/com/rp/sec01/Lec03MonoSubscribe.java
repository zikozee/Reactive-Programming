package com.rp.sec01;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

/**
 * @author : Ezekiel Eromosei
 * @created : 24 Jan, 2022
 */

public class Lec03MonoSubscribe {

    public static void main(String[] args) {

        //publisher
        Mono<Integer> mono = Mono.just("ball")
                .map(String::length)
                        .map(l -> l/1);

        // 1
        //mono.subscribe();

        //2  subscribe(onNext, onErr, onCompleted)
        mono.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
    }

}
