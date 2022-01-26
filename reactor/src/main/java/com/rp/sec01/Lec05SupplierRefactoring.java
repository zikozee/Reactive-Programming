package com.rp.sec01;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @author : Ezekiel Eromosei
 * @created : 24 Jan, 2022
 */

public class Lec05SupplierRefactoring {


    public static void main(String[] args) {

        getName();
        String s = getName()
                .subscribeOn(Schedulers.boundedElastic())  // async
//                .subscribe(Util.onNext());
                .block();
        System.out.println(s);
        getName();
    }

    private static Mono<String> getName() {
        System.out.println("entered getName method");

        //todo info: Always Ensure all yur business logic is ran within the Mono or Flux stuff
        // this way you ensure it is ran lazily
        // hence in this case only "entered getName method" is printed
       return Mono.fromSupplier(() -> {
           System.out.println("Generating name...");
           Util.sleepSecond(3);
           return Util.faker().name().fullName();
       }).map(String::toUpperCase);
    }
}
