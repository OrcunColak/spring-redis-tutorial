package com.colak.springredistutorial.pubsub.service.producer;

import com.colak.springredistutorial.pubsub.service.consumer.RedisEventListener;
import com.redis.testcontainers.RedisContainer;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.awaitility.Awaitility;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
class RedisPublishServiceTest {

    @Container
    @ServiceConnection
    private static final RedisContainer REDIS_CONTAINER = new RedisContainer(DockerImageName.parse("redis:latest"));

    @Getter
    @Slf4j
    public static class TestRedisEventListener extends RedisEventListener {

        private Object message;

        @Override
        public void listen(Object message) {
            log.info("New event received during test: {}", message);
            this.message = message;
        }
    }

    // If defined as inner class, the class needs to be "public static class"
    @TestConfiguration
    public static class RedisEventHandlerConfigTest {
        @Bean
        public RedisEventListener redisEventListener() {
            return new TestRedisEventListener();
        }
    }

    /**
     * This test example also shows that we can @Autowired beans to test methods
     */
    @Test
    void testPublishEvent(@Autowired RedisPublishService redisPublishService,
                          @Autowired TestRedisEventListener redisEventListener) {
        redisPublishService.publishEvent("object");
        Awaitility.await()
                .until(() -> redisEventListener.getMessage() != null);
    }


}
