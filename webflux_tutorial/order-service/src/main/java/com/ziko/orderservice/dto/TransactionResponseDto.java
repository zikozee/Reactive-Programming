package com.ziko.orderservice.dto;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 13 Nov, 2023
 */

public record TransactionResponseDto(Integer userId, Integer amount, TransactionStatus status) {
}
