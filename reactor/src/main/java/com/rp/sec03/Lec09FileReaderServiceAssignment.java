package com.rp.sec03;

import com.rp.courseutil.Util;
import com.rp.sec03.assignment.FileReaderService;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author: Ezekiel Eromosei
 * @created: 03 March 2022
 */

public class Lec09FileReaderServiceAssignment {

    public static void main(String[] args) {

        FileReaderService readerService = new FileReaderService();

        Path path = Paths.get("C:/Users/zikoz/Desktop/JAVA/REACTIVE_PROGRAMMING/reactor/src/main/resources/assignment/sec03/file01.txt");
        readerService.read(path)
                .take(20)
                .subscribe(Util.subscriber());
    }
}
