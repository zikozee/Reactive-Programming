package com.rp.sec08;

import com.rp.courseutil.Util;
import com.rp.sec08.helper.NameGenerator;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 08 Feb, 2024
 */

public class Lec01StartWith {


    public static void main(String[] args) {

        NameGenerator generator = new NameGenerator();
        generator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("sam"));


        generator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("mike"));

        generator.generateNames()
                .take(3)
                .subscribe(Util.subscriber("Jake"));

        generator.generateNames()
                .filter(n -> n.startsWith("A"))
                .take(2)
                .subscribe(Util.subscriber("marshal"));


    }


}
