package com.rp.sec09batching;

import com.rp.courseutil.Util;
import com.rp.sec09batching.helper.BookOrder;
import com.rp.sec09batching.helper.RevenueReport;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 12 Feb, 2024
 */

public class Lec03Assignment {

    public static void main(String[] args) {

        Set<String> allowedCategories = Set.of(
                "Science fiction",
                "Fantasy",
                "Suspense/Thriller"
        );


        bookStream()
                .filter(book -> allowedCategories.contains(book.getCategory()))
                .buffer(Duration.ofSeconds(5))
                .map(Lec03Assignment::revenueCalculator)
                .subscribe(Util.subscriber());


        Util.sleepSecond(60);
    }


    private static RevenueReport revenueCalculator(List<BookOrder> books){
        Map<String, Double> map = books.stream()
                .collect(Collectors.groupingBy(
                        BookOrder::getCategory,
                        Collectors.summingDouble(BookOrder::getPrice)
                ));


        return new RevenueReport(map);
    }

    private static Flux<BookOrder> bookStream(){
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> new BookOrder());
    }
}
