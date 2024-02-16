package com.colak.springredistutorial.crudrepository.repository;

import com.colak.springredistutorial.crudrepository.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// The repository by default uses a bean called redisTemplate
// The default redisTemplate bean uses String serialization
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByExternalId(String externalId);

    List<Customer> findByAccountsId(Long id);

}
