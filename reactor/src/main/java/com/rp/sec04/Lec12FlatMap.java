package com.rp.sec04;

import com.rp.courseutil.Util;
import com.rp.sec04.helper.OrderService;
import com.rp.sec04.helper.Person;
import com.rp.sec04.helper.UserService;
import reactor.core.publisher.Flux;

import java.util.function.Function;


public class Lec12FlatMap {

    // read documentation: Transform the elements emitted by this Flux asynchronously into Publishers,
    // then flatten these inner publishers into a single Flux through merging,
    // which allow them to interleave.

    // concatMap is ordered, flat map is not
    // concatMap uses sequence operator, flat map uses merge operator

    public static void main(String[] args) {
        UserService.getUsers()
                // Mono/ flux
                .flatMap(user -> OrderService.getOrders(user.getUserId()))
                .subscribe(Util.subscriber());

        Util.sleepSecond(60);
    }



}
