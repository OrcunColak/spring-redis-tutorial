package com.colak.springredistutorial.stringvalue.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RateLimitingServiceTest {

    @Autowired
    private RateLimitingService rateLimitingService;

    @Test
    void testIsRateLimited() {
        String clientId = "testClient";

        // Clear any existing rate limit data for the test client
        rateLimitingService.delete(clientId);

        // Test the initial request (below the limit)
        int limit = 5;
        Duration duration = Duration.ofMinutes(1);
        rateLimitingService.setLimit(limit);
        rateLimitingService.setDuration(duration);

        for (int i = 0; i < 5; i++) {
            assertFalse(rateLimitingService.isRateLimited(clientId));
        }

        // Test one more request, exceeding the limit
        assertTrue(rateLimitingService.isRateLimited(clientId));
    }
}
