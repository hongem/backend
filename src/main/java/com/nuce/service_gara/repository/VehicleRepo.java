package com.nuce.service_gara.repository;

import com.nuce.service_gara.model.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepo extends CrudRepository<Vehicle, Integer> {
    Optional<Vehicle> findByRegisterNo(String registerNo);
}
