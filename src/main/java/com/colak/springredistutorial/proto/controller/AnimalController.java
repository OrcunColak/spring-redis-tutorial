package com.colak.springredistutorial.proto.controller;

import com.colak.springredistutorial.proto.dto.AnimalDTO;
import com.colak.springredistutorial.proto.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/animal")
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping("/getAnimal/{id}")
    public AnimalDTO getAnimal(@PathVariable Integer id) {
        return animalService.getAnimal(id);
    }

    @PostMapping("/saveAnimal")
    public void saveAnimal(@RequestBody AnimalDTO animalDTO) {
        animalService.saveAnimal(animalDTO);
    }
}
