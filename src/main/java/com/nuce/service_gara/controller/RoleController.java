package com.nuce.service_gara.controller;

import com.nuce.service_gara.model.Role;
import com.nuce.service_gara.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private RoleRepo roleRepo;

    @GetMapping("/getAll")
    public List<Role> getAll(){
        return roleRepo.findAll();
    }
}
