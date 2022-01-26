package com.rp.sec01.assignment;

import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author : Ezekiel Eromosei
 * @created : 24 Jan, 2022
 */

public class FileService {

    private FileService(){}

    private static final Path PATH = Paths.get("C:/Users/zikoz/Desktop/JAVA/REACTIVE_PROGRAMMING/reactor/src/main/resources/assignment/sec01");

    public static Mono<String> read(String filename){
        return Mono.fromSupplier(() -> readFile(filename));
    }

    public static Mono<Void> write(String filename, String content){
        return Mono.fromRunnable(() -> writeFile(filename, content));
    }

    // todo info:: this is only for notification so we use runnable
    public static Mono<Void> delete(String filename){
        return Mono.fromRunnable(() -> deleteFile(filename));
    }

    // todo info:: this is only for notification so we use runnable
    private static String readFile(String filename){
        try {
            return Files.readString(PATH.resolve(filename));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void writeFile(String filename, String content){
        try {
            Files.writeString(PATH.resolve(filename), content);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void deleteFile(String filename){
        try {
            Files.delete(PATH.resolve(filename));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
