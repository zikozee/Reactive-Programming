package com.ziko.orderservice.service;

import com.ziko.orderservice.client.ProductClient;
import com.ziko.orderservice.client.UserClient;
import com.ziko.orderservice.dto.PurchaseOrderRequestDto;
import com.ziko.orderservice.dto.PurchaseOrderResponseDto;
import com.ziko.orderservice.dto.RequestContext;
import com.ziko.orderservice.entity.PurchaseOrder;
import com.ziko.orderservice.repository.PurchaseOrderRepository;
import com.ziko.orderservice.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.RetrySpec;

import java.time.Duration;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 01 Feb, 2024
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderFulfillmentService {

    private final ProductClient productClient;
    private final UserClient userClient;
    private final PurchaseOrderRepository orderRepository;

    public Mono<PurchaseOrderResponseDto> processOrder(Mono<PurchaseOrderRequestDto> requestDtoMono){

        return requestDtoMono.map(RequestContext::new)
                .flatMap(this::productRequestResponse)
                .doOnNext(EntityDtoUtil::setTransactionRequestDto)
                .flatMap(this::userRequestResponse)
                .doOnError(e -> log.error("ERROR OCCURRED", e))
                .map(EntityDtoUtil::getPurchaseOrder)
//                .publishOn(Schedulers.boundedElastic()) // forces next operator and possibly subsequent operators after the next one to run a separate thread
                .map(po -> {
                    PurchaseOrder save = this.orderRepository.save(po); // blocking
                    return EntityDtoUtil.getPurchaseOrderResponseDto(save);
                }) // blocking
                .subscribeOn(Schedulers.boundedElastic()); // forces previous operator (and possibly operators prior to the previous one) to run a separate thread


    }


    private Mono<RequestContext> productRequestResponse(RequestContext rc){
        return this.productClient.getProductById(rc.getPurchaseOrderRequestDto().productId())
                .doOnNext(rc::setProductDto)
//                .retry(5)
//                .retryWhen(RetrySpec.backoff(5, Duration.ofSeconds(3)))
                .retryWhen(RetrySpec.fixedDelay(5, Duration.ofSeconds(3)))
                .thenReturn(rc);
    }

    private Mono<RequestContext> userRequestResponse(RequestContext rc){
        return this.userClient.authorizeTransaction(rc.getTransactionRequestDto())
                .doOnNext(rc::setTransactionResponseDto)
                .thenReturn(rc);
    }



}
