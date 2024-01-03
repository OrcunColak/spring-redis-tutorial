package com.colak.springredistutorial.proto.service.impl;

import com.colak.springredistutorial.proto.dto.AnimalDTO;
import com.colak.springredistutorial.proto.repository.protobufrepository.AnimalRepository;
import com.colak.springredistutorial.proto.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    @Override
    public AnimalDTO getAnimal(int id) {
        return animalRepository.findById(id);
    }

    @Override
    public void saveAnimal(AnimalDTO animalDTO) {
        animalRepository.save(animalDTO);
    }
}
