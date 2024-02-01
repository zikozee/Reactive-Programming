package com.ziko.orderservice;

import com.ziko.orderservice.client.ProductClient;
import com.ziko.orderservice.client.UserClient;
import com.ziko.orderservice.dto.ProductDto;
import com.ziko.orderservice.dto.PurchaseOrderRequestDto;
import com.ziko.orderservice.dto.PurchaseOrderResponseDto;
import com.ziko.orderservice.dto.UserDto;
import com.ziko.orderservice.service.OrderFulfillmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class OrderServiceApplicationTests {

	@Autowired
	private UserClient userClient;

	@Autowired
	private ProductClient productClient;

	@Autowired
	private OrderFulfillmentService orderFulfillmentService;


	@Test
	void contextLoads() {


		Flux<PurchaseOrderResponseDto> dtoFlux = Flux.zip(userClient.fetchAllUsers(), productClient.getAllProduct())
				.map(t -> Mono.fromSupplier(() -> buildDto(t.getT1(), t.getT2())))
				.flatMap(dto -> orderFulfillmentService.processOrder(dto))
				.doOnNext(System.out::println);


		StepVerifier.create(dtoFlux)
				.expectNextCount(4)
				.verifyComplete();
	}


	private PurchaseOrderRequestDto buildDto(UserDto userDto, ProductDto productDto){
		return new PurchaseOrderRequestDto(userDto.id(), productDto.id());
	}

}
