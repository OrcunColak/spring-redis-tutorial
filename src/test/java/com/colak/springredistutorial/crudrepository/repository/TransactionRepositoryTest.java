package com.colak.springredistutorial.crudrepository.repository;

import com.colak.springredistutorial.crudrepository.domain.Transaction;
import com.redis.testcontainers.RedisContainer;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// The @DataRedisTest annotation injects repository beans
@DataRedisTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
class TransactionRepositoryTest {

    @Container
    @ServiceConnection
    private static final RedisContainer REDIS_CONTAINER = new RedisContainer(DockerImageName.parse("redis:latest"));

    @Autowired
    private TransactionRepository repository;

    @Test
    @Order(1)
    void shouldAdd() {
        Transaction transaction = new Transaction(1L, 1000, new Date(), 20L, 40L);
        transaction = repository.save(transaction);
        assertNotNull(transaction);
    }

    @Test
    @Order(2)
    void shouldFindByFromAccountId() {
        List<Transaction> transactions = repository.findByFromAccountId(20L);
        assertEquals(1, transactions.size());
    }

    @Test
    @Order(3)
    void shouldFindByToAccountId() {
        List<Transaction> transactions = repository.findByToAccountId(40L);
        assertEquals(1, transactions.size());
    }
}
