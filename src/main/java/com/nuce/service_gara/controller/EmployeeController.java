package com.nuce.service_gara.controller;

import com.nuce.service_gara.model.request.CreateEmployeeRequestDTO;
import com.nuce.service_gara.model.request.LoginRequestDTO;
import com.nuce.service_gara.service.serviceImpl.EmployeeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return employeeService.login(loginRequestDTO);
    }

    @PostMapping("/create-or-update")
    public ResponseEntity<?> createOrUpdate(@RequestBody CreateEmployeeRequestDTO employee) {
        return employeeService.createOrUpdate(employee);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return employeeService.getAll();
    }


    @GetMapping("/get/{username}")
    public ResponseEntity<?> getByUsername(@PathVariable String username) {
        return employeeService.getByUsername(username);
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<?> searchByNameOrUsername(@PathVariable(value = "query",required = false) String query) {
        return employeeService.search(query);
    }

//    @DeleteMapping("/{username}")
//    public ResponseEntity<?> delete(@PathVariable String username) {
//        return employeeService.delete(username);
//    }
}
