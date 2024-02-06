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
  - however, the the pipeline is slow probably due to some intensive workload
  - now this results in the subscriber getting data at an very infinitesimal rate 1/s
  - the reactor in context then keeps things ing memory until the subscriber is ready which can cause out of memory
- Strategies:  a way to tell the producer its production rate is too high
  - **buffer**: keep in memory
  - **drop**: Once the queue is full, new items will be dropped
  - **latest**: Once the queue is ful, keep 1 latest item as and when it arrives, drop old
  - **error**: throw error to the downstream