package com.rp.sec08combiningpublishers;

import com.rp.courseutil.Util;
import com.rp.sec08combiningpublishers.helper.AmericanFlights;
import com.rp.sec08combiningpublishers.helper.EmirateFlights;
import com.rp.sec08combiningpublishers.helper.QatarFlights;
import reactor.core.publisher.Flux;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 08 Feb, 2024
 */

public class Lec03Merge {

    public static void main(String[] args) {

        Flux<String> merge = Flux.merge(
                QatarFlights.getFlights(),
                EmirateFlights.getFlights(),
                AmericanFlights.getFlights()
        );


        merge.subscribe(Util.subscriber());

        Util.sleepSecond(10);
    }
}
