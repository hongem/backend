package com.nuce.service_gara.model;

import com.nuce.service_gara.model.enums.EnumStatusReceive;
import com.nuce.service_gara.model.enums.EnumStatusRepair;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SrvInOutGate")
public class InOutGate extends BaseEntity implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "InOutGateId")
    private Integer inOutGateId;

    @NotNull
    private String registerNo;

    private LocalDateTime inDate;

    private LocalDateTime outDate;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "CustomerId")
    private Customer customer;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "EmployeeId")
    private Employee employee;

    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "ServiceId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "SrvRepairService",
            joinColumns = @JoinColumn(name = "InOutGateId"),
            inverseJoinColumns = @JoinColumn(name = "ServiceId"))
    private Set<Service> service;

    @Enumerated(value = EnumType.STRING)
    private EnumStatusReceive vehicleStatusReceive;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    private EnumStatusRepair vehicleStatusRepair;

    @NotNull
    private String cusNameTemp;


}
