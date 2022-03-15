package com.reactive.secondlessonconsumer.config;

import com.reactive.secondlessonconsumer.handler.ProductHandler;
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
    public RouterFunction<ServerResponse> router(ProductHandler productHandler){
        return route()
                .GET("all", productHandler::all)
                .POST("all", productHandler::create)
                .build();
    }
}
