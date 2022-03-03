package com.rp.sec03.helper;

import com.rp.courseutil.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

/**
 * @author: Ezekiel Eromosei
 * @created: 03 March 2022
 */

public class NameProducer implements Consumer<FluxSink<String>> {

    private FluxSink<String> fluxSink; // it is thread safe

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        this.fluxSink = stringFluxSink;
    }

    public void produce(){
        String name = Util.faker().name().fullName();
        String thread = Thread.currentThread().getName();
        this.fluxSink.next(thread + " : " + name);
    }
}
