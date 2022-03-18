package com.leporonitech.appointmentschedule.repositories;

import com.leporonitech.appointmentschedule.model.ExamIncidence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamIncidenceRepository extends JpaRepository<ExamIncidence, Long> {
}
