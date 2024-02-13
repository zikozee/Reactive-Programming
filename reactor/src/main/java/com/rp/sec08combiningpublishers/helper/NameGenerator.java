package com.rp.sec08combiningpublishers.helper;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 08 Feb, 2024
 */

public class NameGenerator {


    private final List<String> list = new ArrayList<>();

    public Flux<String> generateNames(){
        return Flux.generate(stringSynchronousSink -> {
            System.out.println("generated fresh...");
            Util.sleepSecond(1);
            String name = Util.faker().name().firstName();
            list.add(name);
            stringSynchronousSink.next(name);
        })
                .cast(String.class)
                .startWith(getFromCache());
    }

    private Flux<String> getFromCache(){
        return Flux.fromIterable(list);
    }
}
