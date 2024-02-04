package com.colak.springredistutorial.pubsub.service.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedisEventListener {

    public void listen(Object message) {
        log.info("New event received: {}", message);
    }
}
