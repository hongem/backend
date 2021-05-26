package com.nuce.service_gara.service;

import com.nuce.service_gara.model.InOutGate;
import com.nuce.service_gara.model.request.AssignAdvisorRequestDTO;
import com.nuce.service_gara.model.request.CreateOrUpdateQuotationReqDTO;
import com.nuce.service_gara.model.request.IOGSearchRequestDTO;
import com.nuce.service_gara.model.response.InOutGateResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReceptionistService {
    List<InOutGate> test();

    void createIOG(CreateOrUpdateQuotationReqDTO request);

    ResponseEntity<InOutGate> transferStatusReceive(String status, int inOutGateId);

    void updateIOG(CreateOrUpdateQuotationReqDTO request);

    List<InOutGateResponseDTO> searchInOutGate(IOGSearchRequestDTO iogSearchRequestDTO);

    List<InOutGateResponseDTO> getAllInOutGate();

    void assignVehicle(AssignAdvisorRequestDTO input);

}
