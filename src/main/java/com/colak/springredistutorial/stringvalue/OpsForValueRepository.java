package com.colak.springredistutorial.stringvalue;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * See : <a href="https://avi2507.medium.com/unlocking-the-power-of-redis-in-spring-boot-a-comprehensive-guide-with-jedis-1c7078557ae0">...</a>
 * 1.Strings are the basic key-value pairs which can store TEXT, JSON and binary data.
 * <p>
 * 2. Lists are the collection of ordered elements. We can perform operations like pushing elements from front and back,
 * popping elements from front and back, and retrieving ranges.
 * <p>
 * - A Set in Redis is a collection of unique elements with no specific order. Redis sets supports various operations
 * such as adding, removing, and checking for the existence of elements.
 */
@Service
public class OpsForValueRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    private final ValueOperations<String, Object> valueOperations;

    public OpsForValueRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
    }

    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    public void expire(String key, Duration duration) {
        valueOperations.getAndExpire(key, duration);
    }

    public Object get(String key) {
        return valueOperations.get(key);
    }

    public void set(String key, String value) {
        valueOperations.set(key, value);
    }

    public Boolean setIfAbsent(String key, String value, long timeout, TimeUnit unit) {
        return valueOperations.setIfAbsent(key, value, timeout, unit);
    }

    public Long increment(String key) {
        valueOperations.setIfAbsent(key, 0);
        return valueOperations.increment(key, 1);
    }

    public int keySize() {
        Set<String> keys = redisTemplate.keys("*");
        int result = 0;
        if (keys != null) {
            result = keys.size();
        }
        return result;
    }
}
