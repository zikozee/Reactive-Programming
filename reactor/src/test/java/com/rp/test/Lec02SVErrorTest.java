package com.rp.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 16 Feb, 2024
 */

public class Lec02SVErrorTest {

    @Test
    void test2(){
        Flux<Integer> just = Flux.just(1, 2, 3);
        Flux<Integer> oops = Flux.error(new RuntimeException("oops"));
        Flux<Integer> concat = Flux.concat(just, oops);

        StepVerifier
                .create(concat)
                .expectNext(1,2,3)
//                .verifyError();
                .verifyError(RuntimeException.class);
    }


    @Test
    void test3(){
        Flux<Integer> just = Flux.just(1, 2, 3);
        Flux<Integer> oops = Flux.error(new RuntimeException("oops"));
        Flux<Integer> concat = Flux.concat(just, oops);

        StepVerifier
                .create(concat)
                .expectNext(1,2,3)
//                .verifyError();
                .verifyErrorMessage("oops");
    }
}
