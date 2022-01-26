package com.rp.sec01;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

/**
 * @author : Ezekiel Eromosei
 * @created : 24 Jan, 2022
 */

public class Lec08MonoFromRunnable {

    public static void main(String[] args) {

        Runnable runnable = () -> System.out.println("");

//todo infd: best used for notification after successful operation of an event

        Mono.fromRunnable(timeConsumingProcess())
                .subscribe(Util.onNext(), Util.onError(), () -> {
                    System.out.println("process is done. Sending emails... ");
                });
    }


    private static Runnable timeConsumingProcess(){
        return () -> {
            Util.sleepSecond(3);
            System.out.println("Operation Completed");
        };
    }
}
