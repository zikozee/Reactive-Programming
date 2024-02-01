package com.ziko.orderservice.controller;

import com.ziko.orderservice.dto.PurchaseOrderRequestDto;
import com.ziko.orderservice.dto.PurchaseOrderResponseDto;
import com.ziko.orderservice.service.OrderFulfillmentService;
import com.ziko.orderservice.service.OrderQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 01 Feb, 2024
 */
@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class PurchaseOrderController {

    private final OrderFulfillmentService orderFulfillmentService;
    private final OrderQueryService queryService;

    @PostMapping
    public Mono<ResponseEntity<PurchaseOrderResponseDto>> order(@RequestBody Mono<PurchaseOrderRequestDto> purchaseOrderRequestDtoMono){
        return this.orderFulfillmentService.processOrder(purchaseOrderRequestDtoMono)
                .map(ResponseEntity::ok)
                .onErrorReturn(WebClientResponseException.class, ResponseEntity.badRequest().build())
                .onErrorReturn(WebClientRequestException.class, ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build());
    }

    @GetMapping("user/{userId}")
    public Flux<PurchaseOrderResponseDto> getOrderByUserId(@PathVariable int userId){
        return queryService.getProductByUserId(userId);
    }
}
