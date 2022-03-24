package com.leporonitech.appointmentschedule.controller;

import com.leporonitech.appointmentschedule.model.ExamIncidence;
import com.leporonitech.appointmentschedule.repositories.ExamIncidenceRepository;
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
public class ExamIncidenceController {

    private ExamIncidenceRepository examIncidenceRepository;

    public ExamIncidenceController(ExamIncidenceRepository examIncidenceRepository) {
        this.examIncidenceRepository = examIncidenceRepository;
    }

    @GetMapping("/incidences")
    public ResponseEntity<List<ExamIncidence>> findIncidences(){
        List<ExamIncidence> incidendeList = examIncidenceRepository.findAll();
        if (incidendeList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        return new ResponseEntity<>(incidendeList, HttpStatus.OK);
    }

    @GetMapping("/incidence/{id}")
    public ResponseEntity<ExamIncidence> findIncidencesById(@PathVariable Long id){
        Optional<ExamIncidence> incidenceOptional = examIncidenceRepository.findById(id);
        if (incidenceOptional.isPresent()){
            ExamIncidence incidenceUnit = incidenceOptional.get();
            return new ResponseEntity<>(incidenceUnit, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
