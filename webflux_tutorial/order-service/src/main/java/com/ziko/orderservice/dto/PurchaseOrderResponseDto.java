package com.ziko.orderservice.dto;

import lombok.Data;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 01 Feb, 2024
 */

@Data
public class PurchaseOrderResponseDto {

        private Integer userId;
        private String productId;
        private Integer orderId;
        private Integer amount;
        private OrderStatus status;
}
