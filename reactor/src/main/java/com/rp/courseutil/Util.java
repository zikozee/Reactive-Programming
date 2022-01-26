package com.rp.courseutil;

import com.github.javafaker.Faker;

import java.util.function.Consumer;

/**
 * @author : Ezekiel Eromosei
 * @created : 24 Jan, 2022
 */

public class Util {
    private Util(){}

    public static final Faker FAKER = Faker.instance();

    public static Consumer<Object> onNext(){
        return o -> System.out.println("Received: " + o);
    }

    public static Consumer<Throwable> onError(){
        return e -> System.out.println("ERROR : " + e.getMessage());
    }

    public static Runnable onComplete(){
        return () -> System.out.println("Completed");
    }

    public static Faker faker(){
        return FAKER;
    }
    public static void sleepSecond(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
