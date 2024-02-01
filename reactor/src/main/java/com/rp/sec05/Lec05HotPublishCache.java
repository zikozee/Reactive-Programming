package com.rp.sec05;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.stream.Stream;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 20 Nov, 2023
 */

public class Lec05HotPublishCache {


    public static void main(String[] args) {

        // cache = publish.replay()  + stores up to Integer.MAXVALUE
        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(2))
//        .cache(); // stores emitted content and give to new joined subscriber... we can determine how content to store be the overloaded method
//        .cache(3);
                        .cache(2, Duration.ofSeconds(3));

        Util.sleepSecond(2);
        movieStream.subscribe(Util.subscriber("Sam "));

        System.out.println("Mike is about to join");

        Util.sleepSecond(10);

        movieStream
                .subscribe(Util.subscriber("Mike "));

        Util.sleepSecond(60);
    }

    // movie-theatre
    private static Stream<String> getMovie(){
        System.out.println("Got the movie streaming req");

        return Stream.of(
                "Scene 1",
                "Scene 2",
                "Scene 3",
                "Scene 4",
                "Scene 5",
                "Scene 6",
                "Scene 7"
        );
    }
}
