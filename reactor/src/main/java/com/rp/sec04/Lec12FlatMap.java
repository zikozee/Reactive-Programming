package com.rp.sec04;

import com.rp.courseutil.Util;
import com.rp.sec04.helper.OrderService;
import com.rp.sec04.helper.Person;
import com.rp.sec04.helper.UserService;
import reactor.core.publisher.Flux;

import java.util.function.Function;


public class Lec12FlatMap {

    // flat map flattens <Flux<Flux<Object>>>   <Flux<Mono<Object>>>
    // into  Flux<Object>  ::::: asynchronously


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
