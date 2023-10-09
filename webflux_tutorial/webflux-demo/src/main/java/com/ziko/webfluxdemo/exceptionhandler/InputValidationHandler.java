package com.ziko.webfluxdemo.exceptionhandler;

import com.ziko.webfluxdemo.dto.InputFailedValidationResponse;
import com.ziko.webfluxdemo.exception.InputValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InputValidationHandler {

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<InputFailedValidationResponse> handException(InputValidationException ex){

        InputFailedValidationResponse response = new InputFailedValidationResponse(ex.getErrorCode(), ex.getInput(), ex.getMessage());

        return ResponseEntity.badRequest().body(response);
    }
}
