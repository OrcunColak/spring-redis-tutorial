package com.colak.springredistutorial.stringvalue.service;

import com.colak.springredistutorial.stringvalue.OpsForValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RateLimitingService {

    private final OpsForValueRepository opsForValueRepository;
    private static final String RATE_LIMIT_KEY_PREFIX = "ratelimit:";
    private int limit;
    Duration duration;

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public boolean isRateLimited(String clientId) {
        String rateLimitKey = RATE_LIMIT_KEY_PREFIX + clientId;

        Long currentCount = opsForValueRepository.increment(rateLimitKey);

        if (currentCount == null || currentCount == 1) {
            // First request, set expiration time
            opsForValueRepository.expire(rateLimitKey, duration);
        }

        return currentCount != null && currentCount > limit;
    }

    public boolean delete(String clientId) {
        String rateLimitKey = RATE_LIMIT_KEY_PREFIX + clientId;
        return opsForValueRepository.delete(rateLimitKey);
    }
}
