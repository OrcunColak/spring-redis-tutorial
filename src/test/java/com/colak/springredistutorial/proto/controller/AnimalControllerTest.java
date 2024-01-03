package com.colak.springredistutorial.proto.controller;

import com.colak.springredistutorial.proto.dto.AnimalDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AnimalControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testSaveAnimal() {
        String postUrl = "/api/animal/saveAnimal";

        AnimalDTO animalDTO = new AnimalDTO();
        animalDTO.setId(1);
        animalDTO.setName("animal1");
        animalDTO.setAge(10);
        restTemplate.postForObject(postUrl, animalDTO, AnimalDTO.class);

        String getUrl = "/api/animal/getAnimal/1";

        AnimalDTO receivedAnimalDTO = restTemplate.getForObject(getUrl, AnimalDTO.class);

        assertEquals(animalDTO, receivedAnimalDTO);

    }
}
