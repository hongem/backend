package com.nuce.service_gara.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@SqlResultSetMapping(name = "PartResponseDTO", classes = @ConstructorResult(targetClass = PartResponseDTO.class,
        columns = {@ColumnResult(name = "PartId", type = Integer.class),
                @ColumnResult(name = "Name", type = String.class),
                @ColumnResult(name = "Price", type = Integer.class),
                @ColumnResult(name = "ServiceId", type = Integer.class),
                @ColumnResult(name = "ServiceName", type = String.class)
        }))
public class PartResponseDTO {
    private Integer partId;

    private String name;

    private Integer price;

    private Integer serviceId;

    private String serviceName;

}
