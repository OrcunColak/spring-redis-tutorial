package com.colak.springredistutorial.stringvalue.opsforvalue;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

public interface OpsForValueService {

    Boolean delete(String key);

    Boolean setIfAbsent(String key, String value, long timeout, TimeUnit unit);

    @RequiredArgsConstructor
    @Service
    class OpsForValueServiceImpl implements OpsForValueService {

        private final RedisTemplate<String, Object> redisTemplate;

        @Override
        public Boolean delete(String key) {
            return redisTemplate.delete(key);
        }

        @Override
        public Boolean setIfAbsent(String key, String value, long timeout, TimeUnit unit) {
            return redisTemplate.opsForValue().setIfAbsent(key, value, timeout, unit);
        }
    }
}
