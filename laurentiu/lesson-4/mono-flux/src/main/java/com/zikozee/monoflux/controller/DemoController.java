package com.zikozee.monoflux.controller;

import com.zikozee.monoflux.subscribers.DemoSubscriber;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author: Ezekiel Eromosei
 * @created: 26 March 2022
 */

@RestController
public class DemoController {


    @GetMapping(path = "demo")
    public void demo(){
        Flux<Integer> f1 = Flux.just(1,2,3,4,5);

        f1.subscribe(new DemoSubscriber());

        /* NOTES */
    }
}
