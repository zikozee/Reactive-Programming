package com.rp.sec11sink;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 14 Feb, 2024
 */

public class Lec01SinkOne {

    public static void main(String[] args) {

        // mono 1 emitValue / emitEmpty / emitError  --->>> all these call their try versions internally. you can introspect
        Sinks.One<Object> sink = Sinks.one(); // publisher

        Mono<Object> mono = sink.asMono(); // subscriber

        mono.subscribe(Util.subscriber("sam"));
        mono.subscribe(Util.subscriber("mike"));

        sink.tryEmitValue("Hello"); // all subscriber will receive the value

        /*
        
//        sink.tryEmitValue("hi");
//        sink.tryEmitEmpty();
//        sink.tryEmitError(new RuntimeException("err"));
        sink.emitValue("hi", (signalType, emitResult) -> {
            System.out.println(signalType.name());
            System.out.println(emitResult.name());

            return false;
        });

        sink.emitValue("hello", (signalType, emitResult) -> {

            //make some decision with the signalType and emitResult to decide what is returned
            System.out.println(signalType.name());
            System.out.println(emitResult.name());

            return true;
        });


         */
    }
}
