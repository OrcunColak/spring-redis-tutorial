package com.colak.springredistutorial.stringvalue.opsforvalue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class OpsForValueServiceTest {
    @Autowired
    private OpsForValueService opsForValueService;

    @Test
    void testDelete() {
        String key = "key1";
        Boolean result = opsForValueService.setIfAbsent(key, "value", 5L, TimeUnit.SECONDS);
        Assertions.assertEquals(Boolean.TRUE, result);

        result = opsForValueService.delete(key);
        Assertions.assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testGenerateId() {
        Long result = opsForValueService.generateId("mycounter");
        Assertions.assertEquals(Long.valueOf(1), result);
    }
}
