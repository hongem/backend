package com.nuce.service_gara.controller;


import com.nuce.service_gara.model.InOutGate;
import com.nuce.service_gara.model.request.AssignAdvisorRequestDTO;
import com.nuce.service_gara.model.request.CreateOrUpdateQuotationReqDTO;
import com.nuce.service_gara.model.request.IOGSearchRequestDTO;
import com.nuce.service_gara.model.response.InOutGateResponseDTO;
import com.nuce.service_gara.service.serviceImpl.ReceptionistServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/receptionist")
public class ReceptionistController {

    @Autowired
    private ReceptionistServiceImpl service;

    @GetMapping("/get-all-inOutGate")
    public List<InOutGateResponseDTO> getAllInOutGate() {
        log.info("get all record in table SrvInOutGate and custom to InOutGateResponse");
        return service.getAllInOutGate();
    }

    @PostMapping("/create-or-update-inOutGate")
    public ResponseEntity<String> createOrUpdateInOutGate(@RequestBody CreateOrUpdateQuotationReqDTO request) {
        if(request.getInOutGateId() == null){
            log.info("create a draft InOutGate in a receptionist's dashboard");
            try {
                service.createIOG(request);
                return new ResponseEntity("Create Success", HttpStatus.OK);
            } catch (Exception ex) {
                log.error("error " + ex.getMessage());
                return new ResponseEntity("Create Fail", HttpStatus.BAD_REQUEST);
            }
        }else{
            log.info("update a draft InOutGate in a receptionist's dashboard");
            try {
                service.updateIOG(request);
                return new ResponseEntity("Update Success", HttpStatus.OK);
            } catch (Exception ex) {
                log.error("error " + ex.getMessage());
                return new ResponseEntity("Update Fail", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping("/transfer-status-receive")
    public ResponseEntity<InOutGate> transferStatusReceive(@RequestParam String status, @RequestParam int inOutGateId) {
        log.info("switch the status receive");
        return service.transferStatusReceive(status, inOutGateId);
    }

    @PostMapping("/search-inOutGate")
    public List<InOutGateResponseDTO> searchInOutGate(@RequestBody IOGSearchRequestDTO IOGSearchRequestDTO) {
        log.info("search by id, inDate, outDate,... and custom to InOutGateResponse");
        return service.searchInOutGate(IOGSearchRequestDTO);
    }


    @PostMapping("/assign-vehicle")
    public ResponseEntity<String> assignVehicle(@RequestBody AssignAdvisorRequestDTO input) {
        log.info("Assign vehicle to an advisor");
        try {
            service.assignVehicle(input);
            return new ResponseEntity("Assign Success", HttpStatus.OK);
        } catch (Exception ex) {
            log.error("error " + ex.getMessage());
            return new ResponseEntity("Assign Fail", HttpStatus.BAD_REQUEST);
        }
    }
}
