package com.rp.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 16 Feb, 2024
 */

public class Lec07SVContext {

    @Test
    void test1(){
        StepVerifier
                .create(getWelcomeMessage())
                .verifyError(RuntimeException.class);
    }

    @Test
    void test2(){
        StepVerifierOptions options = StepVerifierOptions.create().withInitialContext(Context.of("user", "sam"));

        StepVerifier
                .create(getWelcomeMessage(), options)
                .expectNext("Welcome sam")
                .verifyComplete();
    }

    private static Mono<String> getWelcomeMessage(){
        return Mono.deferContextual(ctx -> {
            if(ctx.hasKey("user")) {
                return Mono.fromSupplier(() -> "Welcome " + ctx.get("user"));
            }else {
                return Mono.error(new RuntimeException("unauthenticated"));
            }
        });
    }

}
