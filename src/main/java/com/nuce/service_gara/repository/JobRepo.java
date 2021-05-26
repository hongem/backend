package com.nuce.service_gara.repository;

import com.nuce.service_gara.model.Job;
import com.nuce.service_gara.model.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends CrudRepository<Job, Integer> {
    String CACHE_JOB = "job";

    @Query(value = "select * from SrvJob where serviceId = ?1", nativeQuery = true)
    List<Job> findByServiceId(int id);
}
