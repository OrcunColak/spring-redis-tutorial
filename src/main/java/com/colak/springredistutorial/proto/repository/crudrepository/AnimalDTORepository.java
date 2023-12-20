package com.colak.springredistutorial.proto.repository.crudrepository;

import com.colak.springredistutorial.proto.dto.AnimalDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalDTORepository extends CrudRepository<AnimalDTO, Integer> {
}
