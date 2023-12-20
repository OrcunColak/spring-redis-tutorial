package com.colak.springredistutorial.proto.repository;

import com.colak.springredistutorial.proto.dto.AnimalDTO;

public interface AnimalRepository {

    AnimalDTO getFromRedisProtobuf(int id);

    void saveToRedisProtobuf(AnimalDTO animalDTO);

}
