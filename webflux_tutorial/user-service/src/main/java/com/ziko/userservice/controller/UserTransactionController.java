package com.ziko.userservice.controller;

import com.ziko.userservice.dto.TransactionRequestDto;
import com.ziko.userservice.dto.TransactionResponseDto;
import com.ziko.userservice.dto.UserDto;
import com.ziko.userservice.entity.UserTransaction;
import com.ziko.userservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 08 Nov, 2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "transaction")
public class UserTransactionController {

    private final TransactionService service;


    @PostMapping
    public Mono<TransactionResponseDto> createTransaction(@RequestBody Mono<TransactionRequestDto> requestDtoMono) {
        return requestDtoMono.flatMap(service::createTransaction);
    }

    @GetMapping
    public Flux<UserTransaction> findByUserId(@RequestParam(value = "userId") int id) {
        return service.getUserTransactionByUserId(id);
    }
}
