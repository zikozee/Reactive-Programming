package com.rp.sec05;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 20 Nov, 2023
 */

public class Lec01ColdPublisher {

    public static void main(String[] args) {

        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(2));

        movieStream.subscribe(Util.subscriber("Sam "));

        Util.sleepSecond(5);

        movieStream
                .subscribe(Util.subscriber("Mike "));

        Util.sleepSecond(60);
    }

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
