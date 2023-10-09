package com.ziko.webfluxdemo.controller;

import com.ziko.webfluxdemo.dto.Response;
import com.ziko.webfluxdemo.exception.InputValidationException;
import com.ziko.webfluxdemo.service.ReactiveMathService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("reactive-math")
@RequiredArgsConstructor
public class ReactiveMathValidationController {

    private final ReactiveMathService reactiveMathService;

    @GetMapping("square/{input}/throw")
    public Mono<Response> findSquare(@PathVariable(value = "input") int input){
        if(input<10 || input > 20)
            throw new InputValidationException(input);

        return this.reactiveMathService.findSquare(input);
    }

}
