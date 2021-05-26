package com.nuce.service_gara.repository;

import com.nuce.service_gara.model.Part;
import com.nuce.service_gara.model.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepo extends CrudRepository<Part, Integer> {
    String CACHE_PART = "part";

    @Cacheable(cacheNames = CACHE_PART)
    @Query(value = "select * from SrvPart where serviceId = ?1", nativeQuery = true)
    List<Part> findByServiceId(int id);
}
