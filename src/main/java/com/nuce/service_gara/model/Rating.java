package com.nuce.service_gara.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SrvRating")
public class Rating extends BaseEntity{
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RatingId")
    private Integer ratingId;

    @NotNull
    private Integer customerId;

    @NotNull
    private Integer employeeId;

    private double star;

    private String comment;
}
