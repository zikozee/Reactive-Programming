package com.rp.sec01;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

/**
 * @author : Ezekiel Eromosei
 * @created : 24 Jan, 2022
 */

public class Lec05MonoFromSupplier {

    public static void main(String[] args) {

        // use .just only wehn you have data already
        //Mono<String> mono = Mono.just(getName());

        // lazy loading no data, no loading

        Supplier<String> stringSupplier = () -> getName();
        Mono<String> mono = Mono.fromSupplier(stringSupplier);
        mono.subscribe(Util.onNext());
        
        
        Callable<String> stringCallable = () -> getName();
        Mono<String> monoCallable = Mono.fromCallable(stringCallable);
        monoCallable.subscribe(
                Util.onNext()
        );

    }

    private static String getName() {
        System.out.println("Generating name...");
        return Util.faker().name().fullName();
    }
}
