package com.nuce.service_gara.controller;

import com.nuce.service_gara.model.Customer;
import com.nuce.service_gara.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/getAll")
    public List<Customer> getAll(){
        return customerRepo.findAll();
    }
}
