# WEBFLUX DEMO

## Flow
 - Accept the request (synchronous or asynchronously) ->> do the processing -->> return a publisher (Mono/Flux)
 - Do not block/subscribe as the backend is **the publisher** while the clients are **the subscriber**

## emitting data when still processing
- by adding the below (produces = MediaType.TEXT_EVENT_STREAM_VALUE) to the controller, the publisher can event data as they are produced
```java

    @GetMapping(value = "table/{input}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)

```

## when subscriber cancels
- in the traditional mvc: we see that even when the user cancels the process **still continues to the end**
  - as the server received no notification
- HOWEVER ,in reactive programming, when the subscriber cancels it halts immediately
  - as the publisher receives instant notification
    - ALSO NOTE: this is generic to reactive webflux and not the MediaType.TEXT_EVENT_STREAM_VALUE
    - test by calling endpoint without produces for flux and observe the same behavior


- note the reason why the data waits for the whole duration before emitting in spring 2.X is because
  - Flux is collected to List before mapping
  - in spring 3.X the collectList() was removed
  - found in **AbstractJackson2Encoder**
![img.png](img.png)


## Reading request ing a non-blocking way
- can receive our payload with (@RequestBody Data data)
- or receive it in a non-blocking way (@RequestBody Mono<RequestBody> Data data)


## ALWAYS CHECK THE REQUEST AND RESPONSE
- Function<? super T, ? extends Mono<? extends R>> transformer
  - means takes in a type T and will return anything that extends Mono<R> or Mono<R>


## Throwing and Handling Exception
- we could throw manually just emit the error signal (see ReactiveMathValidationController endpoint 2)
- **handle** can help us achieve this
- we could handle exception using @RestControllerAdvice

# returning no body with ResponseEntity
- ResponseEntity.badRequest().build()


# ServerResponse
- this is equivalent to ResponseEntity
- use **ServerResponse.ok.bodyValue** for objects like String, BigDecimal, Custom Class
- use **ServerResponse.ok.body(T publisher, Class response)** -> pass in publisher and Type 
- in the request Handler, it should always return, **Mono<ServerResponse>**
  - even if the response is of Type flux, the ServerResponse is an object that contains the actual Response inside of it