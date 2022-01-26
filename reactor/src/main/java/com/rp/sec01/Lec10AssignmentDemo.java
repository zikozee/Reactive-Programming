package com.rp.sec01;

import com.rp.courseutil.Util;
import com.rp.sec01.assignment.FileService;

/**
 * @author : Ezekiel Eromosei
 * @created : 24 Jan, 2022
 */

public class Lec10AssignmentDemo {

    public static void main(String[] args) {

        FileService.read("file03.txt")
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

                FileService.delete("file03.txt")
                        .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

//        FileService.write("file03.txt", "This is file3")
//                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
//

    }
}
