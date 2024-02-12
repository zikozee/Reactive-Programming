package com.rp.sec09batching;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 12 Feb, 2024
 */

public class Lec02OverlapAndDrop {

    public static void main(String[] args) {

        // very good for sampling to see a pattern
        eventStream()
//                .buffer(3, 1) // give me 3 times, whenever a 1 new item comes, remove the oldest and add the latest to the list
                .buffer(5, 2) // give me 5 times, whenever  2 new item comes, remove the oldest 2 and add the latest 2 to the list
                .buffer(3, 5) // give me 3 times, whenever 5 new item comes, take latest 3
                .subscribe(Util.subscriber());


        Util.sleepSecond(60);
    }


    public static Flux<String> eventStream(){
        return Flux.interval(Duration.ofMillis(300))
//                .take(3) // if the event available is lesser than buffer, it takes what's available and completes
                .map(i -> "event"+i);
    }
}
