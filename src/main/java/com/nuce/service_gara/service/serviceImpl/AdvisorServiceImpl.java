package com.nuce.service_gara.service.serviceImpl;

import com.nuce.service_gara.model.request.CreateOrUpdateQuotationReqDTO;
import com.nuce.service_gara.model.request.JobSearchRequestDTO;
import com.nuce.service_gara.model.request.PartSearchRequestDTO;
import com.nuce.service_gara.model.response.InOutGateResponseDTO;
import com.nuce.service_gara.model.response.JobResponseDTO;
import com.nuce.service_gara.model.response.PartResponseDTO;
import com.nuce.service_gara.service.AdvisorService;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvisorServiceImpl implements AdvisorService {
    @Autowired
    private EntityManager em;

    @Override
    public List<JobResponseDTO> getAllJob() {
        List<JobResponseDTO> list = new ArrayList<>();
        StoredProcedureQuery query = em.createStoredProcedureQuery("SearchJob", "JobResponseDTO");
        try {
            query.registerStoredProcedureParameter("JobId", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("JobName", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("ServiceId", String.class, ParameterMode.IN);
            query.setParameter("JobId", 0);
            query.setParameter("JobName", "");
            query.setParameter("ServiceId", "");
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
    public List<JobResponseDTO> searchJob(JobSearchRequestDTO requestDTO) {
        List<JobResponseDTO> list = new ArrayList<>();
        StoredProcedureQuery query = em.createStoredProcedureQuery("SearchJob", "JobResponseDTO");
        try {
            query.registerStoredProcedureParameter("JobId", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("JobName", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("ServiceId", String.class, ParameterMode.IN);
            query.setParameter("JobId", requestDTO.getJobId());
            query.setParameter("JobName", requestDTO.getJobName());
            query.setParameter("ServiceId", requestDTO.getServiceId().stream()
                    .map(n -> String.valueOf(n)).collect(Collectors.joining(", ")));
            query.execute();
            list = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return list;
    }

    @Override
    public List<PartResponseDTO> getAllPart() {
        List<PartResponseDTO> list = new ArrayList<>();
        StoredProcedureQuery query = em.createStoredProcedureQuery("SearchPart", "PartResponseDTO");
        try {
            query.registerStoredProcedureParameter("PartId", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PartName", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("ServiceId", String.class, ParameterMode.IN);
            query.setParameter("PartId", 0);
            query.setParameter("PartName", "");
            query.setParameter("ServiceId", "");
            query.execute();
            list = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return list;
    }

    @Override
    public List<PartResponseDTO> searchPart(PartSearchRequestDTO requestDTO) {
        List<PartResponseDTO> list = new ArrayList<>();
        StoredProcedureQuery query = em.createStoredProcedureQuery("SearchPart", "PartResponseDTO");
        try {
            query.registerStoredProcedureParameter("PartId", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PartName", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("ServiceId", String.class, ParameterMode.IN);
            query.setParameter("PartId", requestDTO.getPartId());
            query.setParameter("PartName", requestDTO.getPartName());
            query.setParameter("ServiceId", requestDTO.getServiceId().stream()
                    .map(n -> String.valueOf(n)).collect(Collectors.joining(", ")));
            query.execute();
            list = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return list;
    }

    @Override
    public void createOrUpdateQuotation(CreateOrUpdateQuotationReqDTO reqDTO) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("UpdateQuotation", "InOutGateResponseDTO");
        query.registerStoredProcedureParameter("InOutGateId", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("RegisterNo", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("CustomerName", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("ServiceId", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("ListObjectJob", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("ListObjectPart", String.class, ParameterMode.IN);
        query.setParameter("InOutGateId", reqDTO.getInOutGateId());
        query.setParameter("RegisterNo", reqDTO.getRegisterNo());
        query.setParameter("CustomerName", reqDTO.getCustomerName());
        query.setParameter("ServiceId", reqDTO.getServiceId().stream()
                .map(n -> String.valueOf(n)).collect(Collectors.joining(", ")));
        query.setParameter("ListObjectJob", reqDTO.getListObjectJob().stream()
                .map(n -> n.toString()).collect(Collectors.joining(", ")));
        query.setParameter("ListObjectPart", reqDTO.getListObjectPart().stream()
                .map(n -> n.toString()).collect(Collectors.joining(", ")));
        query.execute();
    }
}
