package com.colak.springredistutorial.proto.config;

import com.colak.protobuf.AnimalProtos;
import com.colak.springredistutorial.proto.dto.AnimalDTO;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class AnimalProtoRedisSerializer implements RedisSerializer<AnimalDTO> {

    @Override
    public byte[] serialize(AnimalDTO animalDTO) throws SerializationException {
        AnimalProtos.Animal protoMessage = toProtoMessage(animalDTO);
        return protoMessage.toByteArray();
    }

    @Override
    public AnimalDTO deserialize(byte[] bytes) throws SerializationException {
        try {
            AnimalProtos.Animal animalProto = AnimalProtos.Animal.parseFrom(bytes);
            return fromProtoMessage(animalProto);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }

    private AnimalProtos.Animal toProtoMessage(AnimalDTO animalDTO) {
        return AnimalProtos.Animal
                .newBuilder()
                .setId(animalDTO.getId())
                .setName(animalDTO.getName())
                .setAge(animalDTO.getAge())
                .build();
    }

    private AnimalDTO fromProtoMessage(AnimalProtos.Animal animalProto) {
        AnimalDTO animalDTO = new AnimalDTO();
        animalDTO.setId(animalProto.getId());
        animalDTO.setName(animalProto.getName());
        animalDTO.setAge(animalProto.getAge());
        return animalDTO;
    }
}
