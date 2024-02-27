package com.colak.springredistutorial.stringvalue.opsforvalue;

import com.colak.springredistutorial.stringvalue.OpsForValueRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class OpsForValueRepositoryTest {
    @Autowired
    private OpsForValueRepository opsForValueRepository;

    @Test
    void testDelete() {
        String key = "key1";
        Boolean result = opsForValueRepository.setIfAbsent(key, "value", 5L, TimeUnit.SECONDS);
        Assertions.assertEquals(Boolean.TRUE, result);

        result = opsForValueRepository.delete(key);
        Assertions.assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testGenerateId() {
        Long result = opsForValueRepository.generateId("mycounter");
        Assertions.assertEquals(Long.valueOf(1), result);
    }
}
