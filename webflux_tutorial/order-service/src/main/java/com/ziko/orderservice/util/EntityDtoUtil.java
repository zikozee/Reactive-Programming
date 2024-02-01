package com.ziko.orderservice.util;

import com.ziko.orderservice.dto.*;
import com.ziko.orderservice.entity.PurchaseOrder;
import org.springframework.beans.BeanUtils;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 01 Feb, 2024
 */

public class EntityDtoUtil {

    public static void setTransactionRequestDto(RequestContext requestContext){
        TransactionRequestDto requestDto =
                new TransactionRequestDto(requestContext.getPurchaseOrderRequestDto().userId(), requestContext.getProductDto().price());
        requestContext.setTransactionRequestDto(requestDto);
    }

    public static PurchaseOrder getPurchaseOrder(RequestContext requestContext){
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setUserId(requestContext.getPurchaseOrderRequestDto().userId());
        purchaseOrder.setProductId(requestContext.getPurchaseOrderRequestDto().productId());
        purchaseOrder.setAmount(requestContext.getProductDto().price());
        purchaseOrder.setStatus(requestContext.getTransactionResponseDto().status().equals(TransactionStatus.APPROVED) ? OrderStatus.COMPLETED: OrderStatus.FAILED);
        return purchaseOrder;
    }

    public static PurchaseOrderResponseDto getPurchaseOrderResponseDto(PurchaseOrder purchaseOrder){
        PurchaseOrderResponseDto orderResponseDto = new PurchaseOrderResponseDto();
        BeanUtils.copyProperties(purchaseOrder, orderResponseDto);

        orderResponseDto.setOrderId(purchaseOrder.getId());
        return orderResponseDto;
    }
}
