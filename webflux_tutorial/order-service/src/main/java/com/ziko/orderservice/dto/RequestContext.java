package com.ziko.orderservice.dto;

import lombok.Data;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 01 Feb, 2024
 */

@Data
public class RequestContext {

    private PurchaseOrderRequestDto purchaseOrderRequestDto;
    private ProductDto productDto;
    private TransactionRequestDto transactionRequestDto;
    private TransactionResponseDto transactionResponseDto;

    public RequestContext(PurchaseOrderRequestDto purchaseOrderRequestDto) {
        this.purchaseOrderRequestDto = purchaseOrderRequestDto;
    }
}
