package com.nuce.service_gara.controller;

import com.nuce.service_gara.model.request.CreateOrUpdateQuotationReqDTO;
import com.nuce.service_gara.model.request.JobSearchRequestDTO;
import com.nuce.service_gara.model.request.PartSearchRequestDTO;
import com.nuce.service_gara.model.response.JobResponseDTO;
import com.nuce.service_gara.model.response.PartResponseDTO;
import com.nuce.service_gara.service.AdvisorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/advisor")
public class AdvisorController {

    @Autowired
    private AdvisorService advisorService;

    @GetMapping("/get-or-search-job")
    public List<JobResponseDTO> getAllJob(@RequestBody JobSearchRequestDTO requestDTO) {
        List<JobResponseDTO> jobList = new ArrayList<>();
        if (requestDTO.getServiceId() == null) {
            try {
                log.info("get all job");
                jobList = advisorService.getAllJob();
            } catch (Exception ex) {
                log.error("error");
                ex.getMessage();
            }
        } else {
            try {
                log.info("search job");
                jobList = advisorService.searchJob(requestDTO);
            } catch (Exception ex) {
                log.error("error");
                ex.getMessage();
            }
        }
        return jobList;
    }

    @GetMapping("/get-or-search-part")
    public List<PartResponseDTO> getAllPart(@RequestBody PartSearchRequestDTO requestDTO) {
        List<PartResponseDTO> partList = new ArrayList<>();
        if (requestDTO.getServiceId() == null) {
            try {
                log.info("get all part");
                partList = advisorService.getAllPart();
            } catch (Exception ex) {
                log.error("error");
                ex.getMessage();
            }
        } else {
            try {
                log.info("search part");
                partList = advisorService.searchPart(requestDTO);
            } catch (Exception ex) {
                log.error("error");
                ex.getMessage();
            }
        }
        return partList;
    }

    @PostMapping("/create-or-update-quotation")
    public ResponseEntity<String> createOrUpdateQuotation(@RequestBody CreateOrUpdateQuotationReqDTO request){
        log.info("create or update quotation in a advisor's dashboard");
        try {
            advisorService.createOrUpdateQuotation(request);
            return new ResponseEntity("Create Success", HttpStatus.OK);
        } catch (Exception ex) {
            log.error("error " + ex.getMessage());
            return new ResponseEntity("Create Fail", HttpStatus.BAD_REQUEST);
        }
    }
}
