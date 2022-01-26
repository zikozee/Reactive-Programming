package com.rp.sec02.helper;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Ezekiel Eromosei
 * @created : 26 Jan, 2022
 */

public class NameGenerator {

    public static List<String> getNames(int count){
        List<String> list = new ArrayList<>(count);
        for (int i = 0; i <count; i++) {
            list.add(getName());
        }
        return list;
    }

    public static String getName() {
        Util.sleepSecond(1);
        return Util.faker().name().fullName();
    }

    public static Flux<String> getNames2(int count){
        return Flux.range(0, count)
                .map(i -> getName());
    }
}
