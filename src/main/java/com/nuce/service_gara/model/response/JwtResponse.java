package com.nuce.service_gara.model.response;

import com.nuce.service_gara.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@Data
public class JwtResponse {
    private String token;
    private String type;//= "Bearer";
    private String username;
    private Set<Role> role;
}
