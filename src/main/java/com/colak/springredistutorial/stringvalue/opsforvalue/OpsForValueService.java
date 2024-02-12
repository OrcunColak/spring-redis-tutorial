package com.colak.springredistutorial.stringvalue.opsforvalue;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class OpsForValueService {

    private final RedisTemplate<String, Object> redisTemplate;

    public Boolean delete(String key) {
        return redisTemplate.delete(key);
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
