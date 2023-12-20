package com.colak.springredistutorial.proto.service;

import com.colak.springredistutorial.proto.dto.AnimalDTO;

public interface AnimalService {

    AnimalDTO getAnimal(int id);

    void saveAnimal(AnimalDTO animalDTO);
}
