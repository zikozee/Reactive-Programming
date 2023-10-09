package com.ziko.webfluxdemo.dto;

public record InputFailedValidationResponse(int errorCode, int input, String message) {

    @Override
    public String toString() {
        return "InputFailedValidationResponse{" +
                "errorCode=" + errorCode +
                ", input=" + input +
                ", message='" + message + '\'' +
                '}';
    }
}

