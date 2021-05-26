package com.nuce.service_gara.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@SqlResultSetMapping(name = "InOutGateResponseDTO", classes = @ConstructorResult(targetClass = InOutGateResponseDTO.class,
        columns = {@ColumnResult(name = "InOutGateId", type = Integer.class),
                @ColumnResult(name = "InDate", type = LocalDateTime.class),
                @ColumnResult(name = "RegisterNo", type = String.class),
                @ColumnResult(name = "CusNameTemp", type = String.class),
                @ColumnResult(name = "Name", type = String.class),
                @ColumnResult(name = "VehicleStatusReceive", type = String.class),
                @ColumnResult(name = "ServiceId", type = String.class),
                @ColumnResult(name = "EmployeeId", type = Integer.class)
        }))

public class InOutGateResponseDTO {

    private int inOutGateId;

    private LocalDateTime inDate;

    private String registerNo;

    private String cusNameTemp;

    private String name;

    private String vehicleStatusReceive;

    private String serviceId;

    private Integer employeeId;
}
