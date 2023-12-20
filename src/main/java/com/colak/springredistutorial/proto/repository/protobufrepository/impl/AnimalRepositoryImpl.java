package com.colak.springredistutorial.proto.repository.protobufrepository.impl;

import com.colak.springredistutorial.proto.dto.AnimalDTO;
import com.colak.springredistutorial.proto.repository.protobufrepository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AnimalRepositoryImpl implements AnimalRepository {

    private final RedisTemplate<String, Object> redisProtobufTemplate;

    private final String REDIS_HASH_KEY = "myhash";


    @Override
    public void save(AnimalDTO animalDTO) {
        redisProtobufTemplate.opsForHash()
                .put(
                        REDIS_HASH_KEY,
                        animalDTO.getId(),
                        animalDTO
                );
    }

    @Override
    public AnimalDTO findById(int id) {
        return (AnimalDTO) redisProtobufTemplate.opsForHash()
                .get(
                        REDIS_HASH_KEY,
                        id
                );
    }

}
