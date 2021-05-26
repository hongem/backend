package com.nuce.service_gara.service;

import com.nuce.service_gara.model.Part;
import com.nuce.service_gara.model.request.CreateOrUpdateQuotationReqDTO;
import com.nuce.service_gara.model.request.JobSearchRequestDTO;
import com.nuce.service_gara.model.request.PartSearchRequestDTO;
import com.nuce.service_gara.model.response.InOutGateResponseDTO;
import com.nuce.service_gara.model.response.JobResponseDTO;
import com.nuce.service_gara.model.response.PartResponseDTO;

import java.util.List;

public interface AdvisorService {
    List<JobResponseDTO> getAllJob();

    List<JobResponseDTO> searchJob(JobSearchRequestDTO requestDTO);

    List<PartResponseDTO> getAllPart();

    List<PartResponseDTO> searchPart(PartSearchRequestDTO requestDTO);

    void createOrUpdateQuotation(CreateOrUpdateQuotationReqDTO reqDTO);
}
