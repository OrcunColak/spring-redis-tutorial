package com.colak.springredistutorial.proto.repository.protobufrepository;

import com.colak.springredistutorial.proto.dto.AnimalDTO;

public interface AnimalRepository {

    AnimalDTO findById(int id);

    void save(AnimalDTO animalDTO);

}
