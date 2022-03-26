package com.zikozee.monoflux.subscribers;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * @author: Ezekiel Eromosei
 * @created: 26 March 2022
 */

public class DemoSubscriber implements Subscriber<Integer> {

    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println("Subscribed");
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Integer integer) {
        System.out.println("On next " + integer);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("On error " + throwable);
    }

    @Override
    public void onComplete() {
        System.out.println("On Complete");
    }
}
