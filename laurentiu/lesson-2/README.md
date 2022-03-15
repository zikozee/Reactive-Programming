### BOOKS

- REACTIVE SPRING ::: JOSH LONG

### FLOW
![Second Lesson Flow](C:\Users\zikoz\Desktop\JAVA\REACTIVE_PROGRAMMING\laurentiu\lesson-2\diagram.png)

## ERROR HANDLING ON SERVICE

### onErrorResume
- .onErrorResume(e -> Flux.just(p));
- use another service to get some default details ::: ALL EXCEPTIONS never use this
- .onErrorResume(WebClientRequestException.class, e-> Flux.just(p));
- specific exceptions ::: you can have multiple of this or the below
- .onErrorResume(e -> e instanceof WebClientRequestException, e -> Flux.just(p)); 
- this takes a predicate: any boolean (or CONDITION) you can think of or conjure from the error. e.g e -> e.getMessage == something


### onErrorReturn
- we pass the data directly and the flux or publisher  is created for you automatically
- but you don't have access to the Exception

- .onErrorReturn(p); // never use this
- .onErrorReturn(WebClientRequestException.class, p);
- .onErrorReturn(e -> e.getMessage() == null, p);


### onErrorMap
- converting an exception to another exception 3 over-loaded methods
- .onErrorMap(e -> new ProductRetrieveException(e.getMessage()));
- .onErrorMap(WebClientRequestException.class, e -> new ProductRetrieveException(e.getLocalizedMessage()));
- .onErrorMap(e -> e.getMessage() == null, e -> new ProductRetrieveException(e.getLocalizedMessage()));

### onErrorContinue
- skipping or logging an abnormal event  3 over-loaded methods
- we have the ability to apply specific logic on the event that fails
- ```java

.doOnNext(n -> {
      if(n.getName() == null) throw new ProductRetrieveException("product name is null");
  })
  .onErrorContinue((error, event) -> {
      // log error
      System.out.println(error.getMessage());

      //log event to some DATABASE or so something with it

  });
  .onErrorContinue(ProductRetrieveException.class, (e, o) -> e.getMessage());
  .onErrorContinue(e -> e instanceof WebClientRequestException, (e, o) -> e.getMessage());
  ```
