package com.rp.sec07;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.util.concurrent.Queues;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 06 Feb, 2024
 */

public class Lec02DropDemo {

    public static void main(String[] args) {
        // strategy Drop: some published items will be dropped
        // processes 256 see Queues config
//        Queues.SMALL_BUFFER_SIZE
        // let's override below

        // irregular values, 75% will be drained i.e 12  the next item will be pushed into the buffer
        // monitor pushed number after it Receives 12
        System.setProperty("reactor.bufferSize.small", "16"); // without this we get the default set which is 256

        List<Object> droppedItemlist = new ArrayList<>();
        // we can decide to push dropped values in to database, file or somewhere for processing later

        Flux.create(fluxSink -> {
            for (int i = 1; i < 201; i++) {
                fluxSink.next(i);
                System.out.println("Pushed : " + i);
                Util.sleepMillis(1);
            }
            fluxSink.complete();
        })
//                .onBackpressureDrop()  // here
                .onBackpressureDrop(droppedItemlist::add)  // processing dropped items
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    Util.sleepMillis(10);
                })
                .subscribe(Util.subscriber());


        Util.sleepSecond(10);

        System.out.println(droppedItemlist);
    }
}
