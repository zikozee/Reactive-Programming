package com.rp.sec01;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

/**
 * @author : Ezekiel Eromosei
 * @created : 24 Jan, 2022
 */

public class Lec01Stream {

    public static void main(String[] args) {

        Stream<Integer> integerStream = Stream.of(1)
                .map(i ->  {
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    return i*2;
                });

//        System.out.println(integerStream);
        integerStream.forEach(System.out::println);


        Mono<Integer> mono = Mono.empty();

        mono.subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }

}
