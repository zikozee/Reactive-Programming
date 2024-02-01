package com.ziko.orderservice.client;

import com.ziko.orderservice.dto.TransactionRequestDto;
import com.ziko.orderservice.dto.TransactionResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 01 Feb, 2024
 */

@Service
public class UserClient {

    private final WebClient webClient;


    public UserClient(@Value("${user.service.url}") String url) {
        webClient = WebClient.builder()
                .baseUrl(url)
                .build();
    }

    public Mono<TransactionResponseDto> authorizeTransaction(TransactionRequestDto transactionRequestDto){
        return this.webClient
                .post()
                .uri("transaction")
//                .body(transactionRequestDtoMono, TransactionRequestDto.class)
                .bodyValue(transactionRequestDto)
                .retrieve()
                .bodyToMono(TransactionResponseDto.class);
    }
}
