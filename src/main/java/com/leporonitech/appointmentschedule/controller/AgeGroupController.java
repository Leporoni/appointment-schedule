package com.leporonitech.appointmentschedule.controller;

import com.leporonitech.appointmentschedule.model.AgeGroup;
import com.leporonitech.appointmentschedule.repositories.AgeGroupRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AgeGroupController {

    private final AgeGroupRepository ageGroupRepository;

    public AgeGroupController(AgeGroupRepository ageGroupRepository) {
        this.ageGroupRepository = ageGroupRepository;
    }

    @GetMapping("/agegroup")
    public ResponseEntity<?> findAllAgeGroup() {
        try{
            List<AgeGroup> list = ageGroupRepository.findAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/agegroup/{id}")
    public ResponseEntity<AgeGroup> findByIdAgeGroup(@PathVariable Long id){
        try{
            Optional<AgeGroup> ageGroupIdOptional = ageGroupRepository.findById(id);
            if (ageGroupIdOptional.isPresent()){
                AgeGroup ageGroupId = ageGroupIdOptional.get();
                return new ResponseEntity<>(ageGroupId, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/agegroup/novo")
    public AgeGroup newAgeGroup(@RequestBody AgeGroup newGroup){
        return ageGroupRepository.save(newGroup);
    }

    @DeleteMapping("/agegroup/remove/{id}")
    public void deleteAgeGroup(@PathVariable long id){
        ageGroupRepository.deleteById(id);
    }
}
