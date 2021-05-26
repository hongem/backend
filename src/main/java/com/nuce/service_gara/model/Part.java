package com.nuce.service_gara.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SrvPart")
public class Part {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PartId")
    private Integer partId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ServiceId")
    private Service serviceId;

    private String name;

    private double price;
}
