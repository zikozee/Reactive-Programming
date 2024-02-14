package com.rp.sec11sink.assignment;

import lombok.Data;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 14 Feb, 2024
 */

@Data
public class SlackMessage {
    public static final String FORMAT = "[%s -> %s] : %s";

    private String sender;
    private String receiver;
    private String message;

    @Override
    public String toString(){
        return String.format(FORMAT, this.sender, this.receiver, this.message);
    }
}
