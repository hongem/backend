package com.nuce.service_gara.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class DeviceRequestDTO implements Serializable {
    @NotNull
    private String playerId;

    @NotBlank
    private String os;
}


