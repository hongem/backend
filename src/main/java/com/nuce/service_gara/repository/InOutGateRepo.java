package com.nuce.service_gara.repository;

import com.nuce.service_gara.model.InOutGate;
import com.nuce.service_gara.model.response.InOutGateResponseDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.StoredProcedureQuery;
import java.util.List;
import java.util.Optional;

@Repository
public interface InOutGateRepo extends CrudRepository<InOutGate, Integer> {
    List<InOutGate> findAll();

    Optional<InOutGate> findByInOutGateId(int inOutGateId);

    @Query(value = "select * from SrvInOutGate iog where iog.RegisterNo = ?1 " +
            "and iog.OutDate IS NULL and iog.VehicleStatusReceive not like 'REJECT'", nativeQuery = true)
    Optional<InOutGate> checkInOutGate(String registerNo);

    @Modifying
    @Transactional
    @Query(value = "delete from SrvRepairService where InOutGateId = ?1  and ServiceId = ?2", nativeQuery = true)
    void deleteRS(int iOGId, int serviceId);

    @Query(value = "select ServiceId from SrvRepairService where InOutGateId = ?1 ", nativeQuery = true)
    List<Integer> selectServiceId(int IOGId);

    @Modifying
    @Transactional
    @Query(value = "insert into SrvRepairService(InOutGateId, ServiceId) values (?1, ?2) ", nativeQuery = true)
    void insertServiceId(int IOGId, int ServiceId);
}
