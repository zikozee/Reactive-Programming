package com.rp.sec01;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

/**
 * @author : Ezekiel Eromosei
 * @created : 26 Jan, 2022
 */

public class Lec09MonoError {

    public static void main(String[] args) {


        Mono.error(new RuntimeException("just an error"))
                .subscribe(Util.onNext(), Util.onError());

    }
}
