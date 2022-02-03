package com.reactive.secondlesson.config;

import com.reactive.secondlesson.handler.ProductHandler;
import com.reactive.secondlesson.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author : Ezekiel Eromosei
 * @created : 03 Feb, 2022
 */

@Configuration
public class RouteConfig {

    @Bean
    public RouterFunction<ServerResponse> router(ProductHandler productHandler, ProductService productService){
        return route()
                .GET("products", req -> productHandler.getAll(req, productService))
                .build();

    }

    @Bean(name = "router2")
    public RouterFunction<ServerResponse> router(ProductHandler productHandler){   // direct injection
        return route()
                .GET("products", productHandler::getAll)
                .build();

    }
}
