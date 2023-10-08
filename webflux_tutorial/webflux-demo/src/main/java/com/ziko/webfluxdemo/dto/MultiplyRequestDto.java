package com.ziko.webfluxdemo.dto;

public record MultiplyRequestDto(int first, int second) {

    @Override
    public String toString() {
        return "MultiplyRequestDto{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
