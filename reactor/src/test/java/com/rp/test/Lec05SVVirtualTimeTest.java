package com.rp.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 16 Feb, 2024
 */

public class Lec05SVVirtualTimeTest {


    @Test
    void test1(){

        StepVerifier
                // note the flux should have been created as below(i.e timeConsumingFlux()) before introducing it here
                .withVirtualTime(() -> timeConsumingFlux())  // this helps simulates long wait time
                .thenAwait(Duration.ofSeconds(30)) // by providing time of wait which will be assumed to have passed
                .expectNext("1a", "2a", "3a", "4a")
                .verifyComplete();
    }

    @Test
    void test2(){

        //expect subscription
        // do nothing for the first 4 seconds which is when we expect the timeConsumingEvent to be processed
        // then simulate wait time

        StepVerifier
                .withVirtualTime(() -> timeConsumingFlux())
                .expectSubscription() // sub is an event
                .expectNoEvent(Duration.ofSeconds(4)) // first 4 seconds should be idle so we can see it wait truly
                .thenAwait(Duration.ofSeconds(20))
                .expectNext("1a", "2a", "3a", "4a")
                .verifyComplete();
    }


    private Flux<String> timeConsumingFlux(){
        return Flux.range(1, 4)
                .delayElements(Duration.ofSeconds(5))
                .map(i -> i + "a");
    }

}
