##Terminologies
 ===============



### Publisher
- Source
- Observable
- Upstream
- Producer
### Subscriber
- Sink
- Observer
- Downstream
- Consumer

onNext - Consumer<T>
OnError - Consumer<Throwable>
onComplete -  Runnable# Reactive-Programming


# Hot Publishing
- Broadcasting Messages, whether report etc
- instead of giving every user cold publishing
- as soon as the user check the website(subscribes) the hot publisher notices and releases the data based on our config 
  - share (laziest)
  - publish.refCount(1)::: at least one subscriber 
    - it will reconnect later when all the subscriber cancels and some new subscriber appears
  - publish.autoConnect(1):: same as above, but no re-subscription, starts from the section current emitted data is (connects only when the minimum subscriber connects)
  - publish.autoConnect(0): rela hot publisher -  no subscriber required (not ideal)
  - cache(...) - cache emitted item for late subscribers. 
    - say we emit late stock price every 5 minutes and a subscriber join in the 4th minute, we can show the cached data


# Schedulers
- boundedElastic: Network/ time-consuming calls
- parallel: CPU intensive tasks
- A single: Dedicated thread for one-off tasks
- immediate: Current thread
## Operators for Scheduling
- subscribeOn: for upstream
- publishOn: for downstream


# Backpressure/ Overflow Strategy Sec07
- This occurs when the subscriber requests infinite data from the publisher e.g 10000/s
  - however, the pipeline is slow probably due to some intensive workload
  - now this results in the subscriber getting data at a very infinitesimal rate 1/s
  - the reactor in context then keeps things in memory until the subscriber is ready which can cause out of memory
- Strategies:  a way to tell the producer its production rate is too high
  - **buffer**: keep in memory
  - **drop**: Once the queue is full, new items will be dropped
  - **latest**: Once the queue is full, keep 1 latest item as and when it arrives, drop old
  - **error**: throw error to the downstream

# Server sent Events (SSE)
- see Product Service
- where say the frontend(s) or a client(s) needs update at very short intervals
- imagine so many clients calling at the same time, so many load on the backend
- Instead, we have a one-way communication where the backend sends the events or message
- Scenario
  - see config SinkConfig
    - whenever a new product is inserted/updated : the Sinks.Many<T> is called this.sink::tryEmitNext(T t)
    - then the Flux productBroadcast returns the updated Sink  -->> on way connection
    - QUESTION: how can this work for isw-requery when payment is updated
      - ANSWER: we could do a replay().limit(100) to keep as much as 100
    - QUESTION: how to avoid old data/duplicate
      - ANSWER: we could use distinctUntil(/*using ref and timestamp*/)

# Combining Publishers
- startWith - starts from a predefined publisher before the other publisher(s) or publishers aligned sequentially in order you intend to produce from
- concat - emitting one after the completion of the other
- merge
- zip - produces a tuple based on the number of inputs up to 8, greater, you can provide your own implementation
  - if publisherA has 4, publisherB has 5, publisherC has 7 the tuple will take the highest common factor, i.e it will keep emitting until one publisher finish emitting
      - [body,tires,engine]
      - [body,tires,engine]
      - [body,tires,engine]
      - [body,tires,engine]
- combineLatest

## R2DBC Entity Callback:  like hibernate PrePersist and @PreUpdate, : https://www.vinsguru.com/r2dbc-entity-callback/
- BeforeConvert:	To modify an entity object before it is converted into OutboundRow object. Use this to modify Entity object before saving.
- BeforeSave:	Entity object is converted into OutboundRow. We can still modify the domain object. Use this to modify OutboundRow before saving.
- AfterSave:	Entity object is saved at this point. If it was a new entity, ID would have been updated. Use this to modify Entity object after saving.
- AfterConvert:	Entity object is retrieved from DB. Use this to modify Entity object after reading from DB. 

## Spring webflux file upload: https://www.vinsguru.com/spring-webflux-file-upload/

## Batching
- Buffer -this gives a list
- Window - performs like Buffer but gives a flux
- Group

# Repeat & Retry
- say the publisher closes the stream by an onComplete(we use _repeat_) or Error (We use _retry_)
  - now the subscribe wishes to reconnect 
- **repeat**: Resubscribe after complete signal
- **retry**: Resubscribe after error signal