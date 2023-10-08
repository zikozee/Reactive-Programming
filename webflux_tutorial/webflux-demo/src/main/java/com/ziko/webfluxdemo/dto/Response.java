package com.ziko.webfluxdemo.dto;

import java.util.Date;

public record Response(Date date, int output) {
    public Response(int output) {
        this(new Date(), output);
    }

    @Override
    public String toString() {
        return "Response{" +
                "date=" + date +
                ", output=" + output +
                '}';
    }
}
