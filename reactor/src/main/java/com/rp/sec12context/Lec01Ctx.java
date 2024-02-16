package com.rp.sec12context;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 15 Feb, 2024
 */

public class Lec01Ctx {

    public static void main(String[] args) {
        getWelcomeMessage()   // it moves upward the closest context to the subscriber is prioritized
//                .contextWrite(Context.of("user", "jake"))
                .contextWrite(ctx -> ctx.put("user", ctx.get("user").toString().toLowerCase()))
                .contextWrite(Context.of("user", "sam"))
                .subscribe(Util.subscriber());
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
