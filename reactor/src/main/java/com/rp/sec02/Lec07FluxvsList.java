package com.rp.sec02;

import com.rp.courseutil.Util;
import com.rp.sec02.helper.NameGenerator;

import java.util.List;

/**
 * @author : Ezekiel Eromosei
 * @created : 26 Jan, 2022
 */

public class Lec07FluxvsList {
    public static void main(String[] args) {

//        List<String> names = NameGenerator.getNames(5);
//        System.out.println(names);

        NameGenerator.getNames2(5)
                .subscribe(Util.onNext());

    }
}
