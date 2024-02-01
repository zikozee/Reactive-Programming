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