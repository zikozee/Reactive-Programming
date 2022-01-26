package com.rp.sec01;

import reactor.core.publisher.Mono;

/**
 * @author : Ezekiel Eromosei
 * @created : 24 Jan, 2022
 */

public class Lec02MonoJust {

    public static void main(String[] args) {

        //publisher
        Mono<Integer> mono = Mono.just(1);

        System.out.println(mono);

        mono.subscribe(i -> System.out.println("Received: " + i));
        System.out.println("\n====================\n");
        mono.map(i -> i *2)
                .subscribe(i -> System.out.println("Received: " + i));
    }
}
