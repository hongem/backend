package com.nuce.service_gara.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SrvEmployee")
public class Employee extends BaseEntity{

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EmployeeId")
    private Integer employeeId;

    @Column(name = "Name")
    private String name;

    @Column(name = "UserName")
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "Position")
    private String position;

    @Column(name = "Gender")
    private String gender;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(name = "SrvRoleEmployee",
            joinColumns = @JoinColumn(name = "EmployeeId"),
            inverseJoinColumns = @JoinColumn(name = "RoleId"))
    private Set<Role> role = new HashSet<>();

}
