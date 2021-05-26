package com.nuce.service_gara.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class IOGSearchRequestDTO {
    private String registerNo;

    private int InOutGateId;

    private Date inDate;

    private Date outDate;

    private String vehicleStatusReceive;

}
