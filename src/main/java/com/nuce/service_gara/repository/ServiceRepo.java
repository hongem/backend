package com.nuce.service_gara.repository;

import com.nuce.service_gara.model.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepo extends CrudRepository<Service, Integer> {
    String CACHE_SERVICE = "service";

    @Cacheable(cacheNames = CACHE_SERVICE)
    List<Service> findByServiceId(String id);

}
