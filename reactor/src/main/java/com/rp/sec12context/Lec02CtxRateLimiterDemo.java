package com.rp.sec12context;

import com.rp.courseutil.Util;
import com.rp.sec12context.helper.BookService;
import com.rp.sec12context.helper.UserService;
import reactor.util.context.Context;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 16 Feb, 2024
 */

public class Lec02CtxRateLimiterDemo {

    public static void main(String[] args) {

        BookService.getBook()
                .repeat(3)
                .contextWrite(UserService.userCategoryContext())
                .contextWrite(Context.of("user", "mike")) //mike can only call 3, while sam only twce
                .subscribe(Util.subscriber());
    }
}
