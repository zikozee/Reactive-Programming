package com.ziko.orderservice.service;

import com.ziko.orderservice.dto.PurchaseOrderResponseDto;
import com.ziko.orderservice.entity.PurchaseOrder;
import com.ziko.orderservice.repository.PurchaseOrderRepository;
import com.ziko.orderservice.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 01 Feb, 2024
 */

@Service
@RequiredArgsConstructor
public class OrderQueryService {

    private final PurchaseOrderRepository orderRepository;

    public Flux<PurchaseOrderResponseDto> getProductByUserId(int userId){
        return
                Flux.fromStream(() -> orderRepository.findPurchaseOrderByUserId(userId).stream()) //blocking call
                .map(EntityDtoUtil::getPurchaseOrderResponseDto)
                .subscribeOn(Schedulers.boundedElastic());  // forces previous operator (and possibly operators prior to the previous one) to run a separate thread
    }
}
