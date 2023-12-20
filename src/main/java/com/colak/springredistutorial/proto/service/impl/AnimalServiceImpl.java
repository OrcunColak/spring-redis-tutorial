package com.colak.springredistutorial.proto.service.impl;

import com.colak.springredistutorial.proto.dto.AnimalDTO;
import com.colak.springredistutorial.proto.repository.AnimalRepository;
import com.colak.springredistutorial.proto.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    @Override
    public AnimalDTO getAnimal(int id) {
        return animalRepository.getFromRedisProtobuf(id);
    }

    @Override
    public void saveAnimal(AnimalDTO animalDTO) {
        animalRepository.saveToRedisProtobuf(animalDTO);
    }
}
