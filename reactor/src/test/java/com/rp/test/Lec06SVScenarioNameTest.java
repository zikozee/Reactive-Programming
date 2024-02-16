package com.rp.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

import java.time.Duration;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 16 Feb, 2024
 */

public class Lec06SVScenarioNameTest {


    @Test
    void test1(){

        Flux<String> just = Flux.just("a", "b", "c");

        StepVerifierOptions scenarioName = StepVerifierOptions.create().scenarioName("alphabets-test");

        StepVerifier
                .create(just, scenarioName)
                .expectNextCount(12)
                .verifyComplete();
    }


    @Test
    void test3(){

        // by adding the <as> it helps pinpoint which is causing the issue

        Flux<String> just = Flux.just("a", "b1", "c");

        StepVerifier
                .create(just)
                .expectNext("a")
                .as("a-test")
                .expectNext("b")
                .as("b-test")
                .expectNext("c")
                .as("c-test")
                .verifyComplete();
    }

}
