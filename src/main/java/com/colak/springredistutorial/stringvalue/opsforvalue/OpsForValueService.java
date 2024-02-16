package com.colak.springredistutorial.stringvalue.opsforvalue;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * See : https://avi2507.medium.com/unlocking-the-power-of-redis-in-spring-boot-a-comprehensive-guide-with-jedis-1c7078557ae0
 * 1.Strings are the basic key-value pairs which can store TEXT, JSON and binary data.
 * <p>
 * 2. Lists are the collection of ordered elements. We can perform operations like pushing elements from front and back,
 * popping elements from front and back, and retrieving ranges.
 * <p>
 * - A Set in Redis is a collection of unique elements with no specific order. Redis sets supports various operations
 * such as adding, removing, and checking for the existence of elements.
 */
@RequiredArgsConstructor
@Service
public class OpsForValueService {

    private final RedisTemplate<String, Object> redisTemplate;

    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Boolean setIfAbsent(String key, String value, long timeout, TimeUnit unit) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, timeout, unit);
    }

    public Long generateId(String key) {
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        opsForValue.setIfAbsent(key, 0);
        return opsForValue.increment(key, 1);
    }
}
