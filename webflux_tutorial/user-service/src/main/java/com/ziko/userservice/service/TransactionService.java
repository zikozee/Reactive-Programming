package com.ziko.userservice.service;

import com.ziko.userservice.dto.TransactionRequestDto;
import com.ziko.userservice.dto.TransactionResponseDto;
import com.ziko.userservice.dto.TransactionStatus;
import com.ziko.userservice.entity.UserTransaction;
import com.ziko.userservice.repository.UserRepository;
import com.ziko.userservice.repository.UserTransactionRepository;
import com.ziko.userservice.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 13 Nov, 2023
 */

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final UserRepository userRepository;
    private final UserTransactionRepository userTransactionRepository;

    public Mono<TransactionResponseDto> createTransaction(final TransactionRequestDto requestDto){


        return this.userRepository.updateUserBalance(requestDto.userId(), requestDto.amount())
                .filter(Boolean::booleanValue)
                .map(b -> EntityDtoUtil.toEntity(requestDto))
                .flatMap(this.userTransactionRepository::save)
                .map(ut -> EntityDtoUtil.toDto(requestDto, TransactionStatus.APPROVED))
                .defaultIfEmpty(EntityDtoUtil.toDto(requestDto, TransactionStatus.DECLINED));
    }

    public Flux<UserTransaction> getUserTransactionByUserId(int userId) {
        return userTransactionRepository.getUserTransactionByUserId(userId);
    }
}
