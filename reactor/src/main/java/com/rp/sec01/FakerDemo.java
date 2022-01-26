package com.rp.sec01;

import com.github.javafaker.Faker;

/**
 * @author : Ezekiel Eromosei
 * @created : 24 Jan, 2022
 */

public class FakerDemo {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            System.out.println(Faker.instance().name().fullName());
        }

    }
}
