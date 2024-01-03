package com.colak.springredistutorial.proto.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Data
// by default, the key prefix is the fully qualified name of the class plus a colon).
@RedisHash
public class AnimalDTO {

    @Id
    int id;

    @Indexed
//    @Searchable
    String name;

    int age;
}
