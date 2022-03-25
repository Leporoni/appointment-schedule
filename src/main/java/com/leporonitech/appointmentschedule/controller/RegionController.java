package com.leporonitech.appointmentschedule.controller;

import com.leporonitech.appointmentschedule.model.Region;
import com.leporonitech.appointmentschedule.repositories.RegionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RegionController {

    private final RegionRepository regionRepository;

    public RegionController(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @GetMapping("/regions")
    public ResponseEntity<?> findAllRegions(){
        try {
            List<Region> allRegions = regionRepository.findAll();
            System.out.println("Founded");
            if (allRegions.isEmpty())
                System.out.println("Empty");
            return new ResponseEntity<>(allRegions, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("regions/{id}")
    public ResponseEntity<Region> findRegionsById(@PathVariable Long id){
        Optional<Region> regionOptional = regionRepository.findById(id);
        if (regionOptional.isPresent()){
            Region regionUnit = regionOptional.get();
            return new ResponseEntity<>(regionUnit, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
