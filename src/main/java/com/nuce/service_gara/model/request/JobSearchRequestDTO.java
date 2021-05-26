package com.nuce.service_gara.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSearchRequestDTO {
    private Set<Integer> serviceId;

    private Integer jobId;

    private String jobName;
}
