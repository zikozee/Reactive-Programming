package com.rp.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 16 Feb, 2024
 */

public class Lec03SVRangeTest {

    @Test
    void test1(){
        Flux<Integer> range = Flux.range(1, 50);

        StepVerifier
                .create(range)
                .expectNextCount(50)
                .verifyComplete();
    }


    @Test
    void test2(){
        Flux<Integer> range = Flux.range(1, 50);

        StepVerifier
                .create(range)
                .thenConsumeWhile(i -> i < 100)
                .verifyComplete();
    }

}
