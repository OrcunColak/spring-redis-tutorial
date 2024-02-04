package com.colak.springredistutorial.pubsub.service.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
public class RedisEventListener {

    private Consumer<Object> consumer;

    public void setConsumer(Consumer<Object> consumer) {
        this.consumer = consumer;
    }

    public void listen(Object message) {
        log.info("New event received: {}", message);
        consumer.accept(message);
    }
}
