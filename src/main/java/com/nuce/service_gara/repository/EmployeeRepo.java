package com.nuce.service_gara.repository;

import com.nuce.service_gara.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Integer> {
    Optional<Employee> findByUsername(String username);
    List<Employee> findAll();

    @Query(value = "SELECT UserName FROM SrvEmployee", nativeQuery = true)
    List<Employee> test();

    void delete(Employee employee);
}
