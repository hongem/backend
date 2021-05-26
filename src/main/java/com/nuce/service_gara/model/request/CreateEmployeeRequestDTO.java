package com.nuce.service_gara.model.request;

import com.nuce.service_gara.model.Role;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class CreateEmployeeRequestDTO {

    private int employeeId;
    private String name;

    private String username;

    private String password;

    private String position;

    private String gender;

    private Set<Role> role;
}
