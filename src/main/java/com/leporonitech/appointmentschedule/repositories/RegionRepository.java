package com.leporonitech.appointmentschedule.repositories;

import com.leporonitech.appointmentschedule.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
