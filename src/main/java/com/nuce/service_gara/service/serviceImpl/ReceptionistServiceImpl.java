package com.nuce.service_gara.service.serviceImpl;

import com.nuce.service_gara.model.InOutGate;
import com.nuce.service_gara.model.enums.EnumStatusReceive;
import com.nuce.service_gara.model.enums.EnumStatusRepair;
import com.nuce.service_gara.model.request.AssignAdvisorRequestDTO;
import com.nuce.service_gara.model.request.CreateOrUpdateQuotationReqDTO;
import com.nuce.service_gara.model.request.IOGSearchRequestDTO;
import com.nuce.service_gara.model.response.InOutGateResponseDTO;
import com.nuce.service_gara.repository.InOutGateRepo;
import com.nuce.service_gara.service.ReceptionistService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReceptionistServiceImpl implements ReceptionistService {

    @Autowired
    private InOutGateRepo inOutGateRepo;

    @Autowired
    private EntityManager em;

    @Override
    public List<InOutGate> test() {
        return inOutGateRepo.findAll();
    }

    @Override
    public void createIOG(CreateOrUpdateQuotationReqDTO request) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("CreateInOutGateProc", "InOutGateResponseDTO");
        query.registerStoredProcedureParameter("RegisterNo", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("ServiceId", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("CusNameTemp", String.class, ParameterMode.IN);
        query.setParameter("RegisterNo", request.getRegisterNo());
//        query.setParameter("ServiceId", request.getServiceId().stream()
//                .map(n -> String.valueOf(n)).collect(Collectors.joining(", ")));
        query.setParameter("CusNameTemp", request.getCustomerName());
        query.execute();
    }

    @Override
    public ResponseEntity<InOutGate> transferStatusReceive(String status, int inOutGateId) {
        try {
            Optional<InOutGate> inOutGate = inOutGateRepo.findByInOutGateId(inOutGateId);
            switch (status) {
                case "DOING":
                    inOutGate.get().setVehicleStatusReceive(EnumStatusReceive.DOING);
                    break;
                case "CHECK":
                    inOutGate.get().setVehicleStatusReceive(EnumStatusReceive.CHECK);
                    break;
                case "REJECT":
                    inOutGate.get().setVehicleStatusReceive(EnumStatusReceive.REJECT);
                    break;
                case "DONE":
                    inOutGate.get().setVehicleStatusReceive(EnumStatusReceive.DONE);
                    inOutGate.get().setVehicleStatusRepair(EnumStatusRepair.DOING);
                    break;
            }

            inOutGateRepo.save(inOutGate.get());
            return new ResponseEntity<>(inOutGate.get(), HttpStatus.OK);
        } catch (NullPointerException ex) {
            log.error("not found!!");
            return new ResponseEntity("failed", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void updateIOG(CreateOrUpdateQuotationReqDTO request) {
        List<Integer> listServiceId = inOutGateRepo.selectServiceId(request.getInOutGateId());
        Set<Integer> old = request.getServiceId();
        List<Integer> listDelete = new ArrayList<>(listServiceId);
        listDelete.removeAll(old);
        listDelete.stream().forEach(t -> {
            inOutGateRepo.deleteRS(request.getInOutGateId(), t);
        });

        List<Integer> listInsert = new ArrayList<>(old);
        listInsert.removeAll(listServiceId);
        listInsert.stream().forEach(t -> inOutGateRepo.insertServiceId(request.getInOutGateId(), t));

        List<Integer> listUpdate = new ArrayList<>(old);
        listUpdate.removeAll(listInsert);
        System.out.println(listUpdate);

        Optional<InOutGate> inOutGate = inOutGateRepo.findByInOutGateId(request.getInOutGateId());

        Optional<InOutGate> checkCarInGara = inOutGateRepo.checkInOutGate(request.getRegisterNo());
        log.info("check car with registerNo, outDate < now and status is not 'reject' ");
        if (checkCarInGara.isEmpty()) {
            inOutGate.get().setCusNameTemp(request.getCustomerName());
            inOutGate.get().setRegisterNo(request.getRegisterNo());
        } else {
            if (inOutGate.get().getRegisterNo().equals(request.getRegisterNo())) {
                inOutGate.get().setCusNameTemp(request.getCustomerName());
                inOutGate.get().setRegisterNo(request.getRegisterNo());
            }
        }
        inOutGateRepo.save(inOutGate.get());
    }

    @Override
    public List<InOutGateResponseDTO> searchInOutGate(IOGSearchRequestDTO IOGSearchRequestDTO) {
        List<InOutGateResponseDTO> list = new ArrayList<>();
        StoredProcedureQuery query = em.createStoredProcedureQuery("SearchInOutGate", "InOutGateResponseDTO");
        try {
            query.registerStoredProcedureParameter("RegisterNo", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("InOutGateId", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("InDate", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("OutDate", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("VehicleStatusReceive", String.class, ParameterMode.IN);
            query.setParameter("RegisterNo", IOGSearchRequestDTO.getRegisterNo());
            query.setParameter("InOutGateId", IOGSearchRequestDTO.getInOutGateId());
            query.setParameter("InDate", IOGSearchRequestDTO.getInDate());
            query.setParameter("OutDate", IOGSearchRequestDTO.getOutDate());
            query.setParameter("VehicleStatusReceive", IOGSearchRequestDTO.getVehicleStatusReceive());
            query.execute();
            list = query.getResultList();
        } finally {
            try {
                query.unwrap(ProcedureOutputs.class).release();
            } catch (Exception e) {

            }
        }
        return list;
    }

    @Override
    public List<InOutGateResponseDTO> getAllInOutGate() {
        List<InOutGateResponseDTO> list = new ArrayList<>();
        StoredProcedureQuery query = em.createStoredProcedureQuery("SearchInOutGate", "InOutGateResponseDTO");
        try {
            query.registerStoredProcedureParameter("RegisterNo", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("InOutGateId", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("InDate", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("OutDate", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("VehicleStatusReceive", String.class, ParameterMode.IN);
            query.setParameter("RegisterNo", "");
            query.setParameter("InOutGateId", 0);
            query.setParameter("InDate", null);
            query.setParameter("OutDate", null);
            query.setParameter("VehicleStatusReceive", "");

            query.execute();
            list = query.getResultList();
        } finally {
            try {
                query.unwrap(ProcedureOutputs.class).release();
            } catch (Exception e) {

            }
        }
        return list;
    }

    @Override
    public void assignVehicle(AssignAdvisorRequestDTO input) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("AssignVehicle", "InOutGateResponseDTO");
        query.registerStoredProcedureParameter("InOutGateId", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("EmployeeId", Integer.class, ParameterMode.IN);
        query.setParameter("InOutGateId", input.getInOutGateId());
        query.setParameter("EmployeeId", input.getEmployeeId());
        query.execute();
    }
}
