package com.nuce.service_gara.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateQuotationReqDTO {

    private Integer inOutGateId;

    private String registerNo;

    private Set<Integer> serviceId;

    private String customerName;

    private Set<QuotationJobReqDTO> listObjectJob;

    private Set<QuotationPartReqDTO> listObjectPart;
}
