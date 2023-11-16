package com.ziko.userservice.util;

import com.ziko.userservice.dto.TransactionRequestDto;
import com.ziko.userservice.dto.TransactionResponseDto;
import com.ziko.userservice.dto.TransactionStatus;
import com.ziko.userservice.dto.UserDto;
import com.ziko.userservice.entity.User;
import com.ziko.userservice.entity.UserTransaction;

import java.time.LocalDateTime;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 13 Nov, 2023
 */

public final class EntityDtoUtil {

    public static UserDto toDto(User user){
        return new UserDto(user.getId(), user.getName(), user.getBalance());
    }

    public static User toEntity(UserDto dto){
        User user = new User();
        user.setId(dto.id());
        user.setName(dto.name());
        user.setBalance(dto.balance());
        return user;
    }

    public static UserTransaction toEntity(TransactionRequestDto requestDto){
        UserTransaction ut = new UserTransaction();
        ut.setUserId(requestDto.userId());
        ut.setAmount(requestDto.amount());
        ut.setTransactionDate(LocalDateTime.now());
        return ut;
    }

    public static TransactionResponseDto toDto(TransactionRequestDto requestDto, TransactionStatus status){
        return new TransactionResponseDto(requestDto.userId(), requestDto.amount(), status);
    }
}
