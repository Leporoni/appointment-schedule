package com.leporonitech.appointmentschedule.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String region;
    private Integer total_exams;
}
