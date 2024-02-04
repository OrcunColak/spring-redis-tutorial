package com.colak.springredistutorial.pubsub.service.producer;

import com.colak.springredistutorial.pubsub.service.consumer.RedisEventListener;
import com.redis.testcontainers.RedisContainer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

/**
 * Test that uses mock override for listener
 */
@SpringBootTest
@Testcontainers
class RedisPublishServiceUsingMockTest {

    @Container
    @ServiceConnection
    private static final RedisContainer REDIS_CONTAINER = new RedisContainer(DockerImageName.parse("redis:latest"));

    @MockBean
    private RedisEventListener redisEventListener;

    /**
     * This test example also shows that we can @Autowired beans to test methods
     */
    @Test
    void testPublishEvent(@Autowired RedisPublishService redisPublishService,
                          @Autowired RedisEventListener redisEventListener) {
        redisPublishService.publishEvent("object");

        Mockito.verify(redisEventListener, Mockito.timeout(1000).times(1))
                .listen(Mockito.any());
    }

}
