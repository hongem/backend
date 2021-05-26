package com.nuce.service_gara.model;

import com.nuce.service_gara.config.WebConfig;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Import;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SrvCustomer")
public class Customer extends BaseEntity {

    @Id
    @NotNull
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "CustomerId")
    private Integer customerId;

    private String name;

    private String address;

    private String phoneNumber;

    private Date birthday;

    private String gender;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(	name = "SrvVehicleCustomer",
            joinColumns = @JoinColumn(name = "CustomerId"),
            inverseJoinColumns = @JoinColumn(name = "VehicleId"))
    private Set<Vehicle> vehicles = new HashSet<>();



}