package com.colak.springredistutorial;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LockControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    void testLock1() {
        String getUrl = "/api/lock/perform/lock1";

        String result = restTemplate.getForObject(getUrl, String.class);

        assertEquals("Operation completed", result);
    }

    @Test
    void testLock2() {
        String key = "lock2";
        redisTemplate.opsForValue().setIfAbsent(key, "my_value");

        String getUrl = "/api/lock/perform/lock2";

        String result = restTemplate.getForObject(getUrl, String.class);

        assertEquals("Operation failed", result);
    }

}
