package com.rp.sec06threadingandschedulers;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 02 Feb, 2024
 */

public class Lec0lParallelDemo {

    public static void main(String[] args) {


        Flux.range(1, 10)
                .parallel(2)
                .runOn(Schedulers.boundedElastic())
                .doOnNext(i -> printThreadName("next " + i))
                .subscribe(v -> printThreadName("sub " + v));



        Util.sleepSecond(5);
    }

    private static void printThreadName(String msg){
        System.out.println(msg + "\t\t: Thread " + Thread.currentThread().getName());
    }
}
