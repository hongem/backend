package com.nuce.service_gara.service;

import com.nuce.service_gara.model.Employee;
import com.nuce.service_gara.model.request.CreateEmployeeRequestDTO;
import com.nuce.service_gara.model.request.LoginRequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    ResponseEntity<?> login(LoginRequestDTO loginRequestDTO);

    ResponseEntity<?> createOrUpdate(CreateEmployeeRequestDTO employeeRequest);

    ResponseEntity<?> getAll();

    ResponseEntity<?> update(CreateEmployeeRequestDTO employeeRequest);

    ResponseEntity<?> getByUsername(String username);

    ResponseEntity<?> search(String query);

//    ResponseEntity<?> delete(String username);
}
