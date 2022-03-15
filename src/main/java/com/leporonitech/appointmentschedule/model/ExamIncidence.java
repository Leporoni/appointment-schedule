package com.leporonitech.appointmentschedule.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ExamIncidence {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, updatable = false)
    private Long id;
    private Integer region_id;
    private Integer month;
    private Long group_id;
    private Integer amount_exams;
}
