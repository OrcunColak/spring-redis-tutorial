package com.colak.springredistutorial.crudrepository.repository;

import com.colak.springredistutorial.crudrepository.domain.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// The repository by default uses a bean called redisTemplate
// The default redisTemplate bean uses String serialization
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    List<Transaction> findByFromAccountId(Long fromAccountId);

    List<Transaction> findByToAccountId(Long toAccountId);

}
