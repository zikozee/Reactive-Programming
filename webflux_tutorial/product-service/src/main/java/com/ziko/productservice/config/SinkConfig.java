package com.ziko.productservice.config;

import com.ziko.productservice.dto.ProductDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 07 Feb, 2024
 */

@Configuration
public class SinkConfig {

    @Bean
    public Sinks.Many<ProductDto> sink(){
        return Sinks.many()
                .replay()
                .limit(1);
    }

    @Bean
    public Flux<ProductDto> productBroadcast(Sinks.Many<ProductDto> sink){
        return sink.asFlux();
    }
}
