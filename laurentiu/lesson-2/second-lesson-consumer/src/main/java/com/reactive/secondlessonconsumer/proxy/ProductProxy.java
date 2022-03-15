package com.reactive.secondlessonconsumer.proxy;

import com.reactive.secondlessonconsumer.exceptions.ProductRetrieveException;
import com.reactive.secondlessonconsumer.handler.ProductHandler;
import com.reactive.secondlessonconsumer.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : Ezekiel Eromosei
 * @created : 03 Feb, 2022
 */

@Component
@RequiredArgsConstructor
public class ProductProxy {

    private final WebClient webClient;

    public Flux<Product> getAll(){
        var p = new Product();
        p.setName("Default");

        return webClient.get().uri("/products")
                .exchangeToFlux(res -> res.bodyToFlux(Product.class))
//                .onErrorResume(e -> Flux.just(p)); // use another service to get some default details ::: ALL EXCEPTIONS never use this
//                .onErrorResume(WebClientRequestException.class, e-> Flux.just(p)); // specific exceptions ::: you can have multiple of this or the below
//                .onErrorResume(e -> e instanceof WebClientRequestException, e -> Flux.just(p)); //  this takes a predicate: any boolean (or CONDITION) you can think of or conjure from the error. e.g e -> e.getMessage == something

                /* ON ERROR RETURN */
        //we pass the data directly and the flux or publisher  is created for you automatically but you don't have access to the Exception

//                .onErrorReturn(p); // never use this
//                .onErrorReturn(WebClientRequestException.class, p);
//                .onErrorReturn(e -> e.getMessage() == null, p);


                /* ON ERROR MAP  */
        // converting an exception to another exception 3 over-loaded methods
//                .onErrorMap(e -> new ProductRetrieveException(e.getMessage()));
//                .onErrorMap(WebClientRequestException.class, e -> new ProductRetrieveException(e.getLocalizedMessage()));
//                .onErrorMap(e -> e.getMessage() == null, e -> new ProductRetrieveException(e.getLocalizedMessage()));

              /* ON ERROR CONTINUE */
        // skipping or logging an abnormal event  3 over-loaded methods
        // we have the ability to apply specific logic on the event that fails
//                .doOnNext(n -> {
//                    if(n.getName() == null) throw new ProductRetrieveException("product name is null");
//                })
//                .onErrorContinue((error, event) -> {
//                    // log error
//                    System.out.println(error.getMessage());
//
//                    //log event to some DATABASE or so something with it
//
//                });
//                .onErrorContinue(ProductRetrieveException.class, (e, o) -> e.getMessage());
//                .onErrorContinue(e -> e instanceof WebClientRequestException, (e, o) -> e.getMessage());


                /*  RETRY  */
        // retying in case it fails
//                .retry();
                .retry(3);
    }

    public Mono<Product> createProduct(Product product){
        return webClient.post().uri("/products")
                .body(BodyInserters.fromValue(product))
                .exchangeToMono(res -> res.bodyToMono(Product.class));
    }
}
