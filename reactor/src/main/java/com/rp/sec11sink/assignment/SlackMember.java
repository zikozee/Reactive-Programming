package com.rp.sec11sink.assignment;

import lombok.Getter;

import java.util.function.Consumer;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 14 Feb, 2024
 */

public class SlackMember {

    private String name;
    private Consumer<String> messageConsumer;

    public SlackMember(String name) {
        this.name = name;
    }

    String getName(){
        return name;
    }

    void receives(String message){
        System.out.println(message);
    }

    public void says(String message){
        this.messageConsumer.accept(message);
    }

    void setMessageConsumer(Consumer<String> messageConsumer) {
        this.messageConsumer = messageConsumer;
    }
}
