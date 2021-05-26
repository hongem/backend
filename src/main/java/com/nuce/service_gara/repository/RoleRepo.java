package com.nuce.service_gara.repository;

import com.nuce.service_gara.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepo extends CrudRepository<Role, Integer> {
    List<Role> findAll();
}