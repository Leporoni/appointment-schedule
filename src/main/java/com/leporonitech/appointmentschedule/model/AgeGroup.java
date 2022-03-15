package com.leporonitech.appointmentschedule.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AgeGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, updatable = false)
    private Long id;
    private Integer group_i;
    private Integer group_n;
    private String description;
}