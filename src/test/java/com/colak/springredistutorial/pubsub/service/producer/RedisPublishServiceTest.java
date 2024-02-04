package com.colak.springredistutorial.pubsub.service.producer;

import com.colak.springredistutorial.pubsub.service.consumer.RedisEventListener;
import com.redis.testcontainers.RedisContainer;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.awaitility.Awaitility;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.function.Consumer;

@SpringBootTest
@Testcontainers
class RedisPublishServiceTest {

    @Container
    @ServiceConnection
    private static final RedisContainer REDIS_CONTAINER = new RedisContainer(DockerImageName.parse("redis:latest"));

    /**
     * This test example also shows that we can @Autowire beans to test methods
     */
    @Test
    void testPublishEvent(@Autowired RedisPublishService redisPublishService,
                          @Autowired RedisEventListener redisEventListener) {
        MyConsumer myConsumer = new MyConsumer();
        redisEventListener.setConsumer(myConsumer);

        redisPublishService.publishEvent("object");
        Awaitility.await().atMost(Duration.ofSeconds(10))
                .until(() -> myConsumer.getMessage() != null);
    }

    @Getter
    private static class MyConsumer implements Consumer<Object> {
        private Object message;

        @Override
        public void accept(Object message) {
            this.message = message;
        }
    }
}
