package com.rp.sec01;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/**
 * @author : Ezekiel Eromosei
 * @created : 24 Jan, 2022
 */

public class Lec07MonoFromFuture {

    public static void main(String[] args) {

        Mono.fromFuture(getName())
                .subscribe(Util.onNext());

        Util.sleepSecond(1);
    }


    private static CompletableFuture<String> getName(){
        return CompletableFuture.supplyAsync(() -> Util.faker().name().fullName());
    }
}
