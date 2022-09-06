package com.example.springreactivestreaming.processor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkProcessor<T> {

    private final Sinks.Many<T> sink;

    public SinkProcessor() {
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
    }

    //emits an object of the generic T class into the sink
    public void add(T t) {
        this.sink.tryEmitNext(t);
    }

    //return the Flux of T
    public Flux<T> flux() {
        return this.sink.asFlux();
    }
}