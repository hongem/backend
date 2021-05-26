package com.nuce.service_gara.repository;

import com.nuce.service_gara.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Integer> {
    List<Customer> findAll();
    Optional<Customer> findByCustomerId(int customerId);
}
