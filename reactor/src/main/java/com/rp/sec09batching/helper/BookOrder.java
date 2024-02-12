package com.rp.sec09batching.helper;

import com.github.javafaker.Book;
import com.rp.courseutil.Util;
import lombok.Getter;
import lombok.ToString;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 12 Feb, 2024
 */

@Getter
@ToString
public class BookOrder {

    private final String title;
    private final String author;
    private final String category;
    private final double price;

    public BookOrder() {
        Book book = Util.faker().book();
        this.title = book.title();
        this.author = book.author();
        this.category = book.genre();
        this.price = Double.parseDouble(Util.faker().commerce().price());
    }
}
