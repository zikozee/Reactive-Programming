package com.ziko.webfluxdemo.dto;

import java.util.Date;

public record Response(Date date, double output) {
    public Response(double output) {
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
