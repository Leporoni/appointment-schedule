package com.leporonitech.appointmentschedule.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.leporonitech.appointmentschedule.model.ExamIncidence;
import com.leporonitech.appointmentschedule.repositories.ExamIncidenceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExamIncidenceControllerTest {

    @Test
    void testFindIncidences() {
        ExamIncidenceRepository examIncidenceRepository = mock(ExamIncidenceRepository.class);
        when(examIncidenceRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<ExamIncidence>> actualFindIncidencesResult = (new ExamIncidenceController(
                examIncidenceRepository)).findIncidences();
        assertNull(actualFindIncidencesResult.getBody());
        assertEquals("<501 NOT_IMPLEMENTED Not Implemented,[]>", actualFindIncidencesResult.toString());
        assertEquals(HttpStatus.NOT_IMPLEMENTED, actualFindIncidencesResult.getStatusCode());
        assertTrue(actualFindIncidencesResult.getHeaders().isEmpty());
        verify(examIncidenceRepository).findAll();
    }

    @Test
    void testFindIncidences2() {
        ExamIncidence examIncidence = new ExamIncidence();
        examIncidence.setGroup_id(0L);
        examIncidence.setId(123L);
        examIncidence.setRegion_id(1);
        examIncidence.setAmount_exams(10);
        examIncidence.setMonth(0);

        ArrayList<ExamIncidence> examIncidenceList = new ArrayList<>();
        examIncidenceList.add(examIncidence);
        ExamIncidenceRepository examIncidenceRepository = mock(ExamIncidenceRepository.class);
        when(examIncidenceRepository.findAll()).thenReturn(examIncidenceList);
        ResponseEntity<List<ExamIncidence>> actualFindIncidencesResult = (new ExamIncidenceController(
                examIncidenceRepository)).findIncidences();
        assertEquals("<200 OK OK,[ExamIncidence(id=123, region_id=1, month=0, group_id=0, amount_exams=10)],[]>",
                actualFindIncidencesResult.toString());
        assertTrue(actualFindIncidencesResult.hasBody());
        assertEquals(HttpStatus.OK, actualFindIncidencesResult.getStatusCode());
        assertTrue(actualFindIncidencesResult.getHeaders().isEmpty());
        verify(examIncidenceRepository).findAll();
    }

    @Test
    void testFindIncidencesById() {
        ExamIncidence examIncidence = new ExamIncidence();
        examIncidence.setGroup_id(1L);
        examIncidence.setId(123L);
        examIncidence.setRegion_id(1);
        examIncidence.setAmount_exams(10);
        examIncidence.setMonth(1);
        ExamIncidenceRepository examIncidenceRepository = mock(ExamIncidenceRepository.class);
        when(examIncidenceRepository.findById((Long) any())).thenReturn(Optional.of(examIncidence));
        ResponseEntity<ExamIncidence> actualFindIncidencesByIdResult = (new ExamIncidenceController(
                examIncidenceRepository)).findIncidencesById(123L);
        assertEquals("<200 OK OK,ExamIncidence(id=123, region_id=1, month=1, group_id=1, amount_exams=10),[]>",
                actualFindIncidencesByIdResult.toString());
        assertTrue(actualFindIncidencesByIdResult.getHeaders().isEmpty());
        assertTrue(actualFindIncidencesByIdResult.hasBody());
        assertEquals(HttpStatus.OK, actualFindIncidencesByIdResult.getStatusCode());
        verify(examIncidenceRepository).findById((Long) any());
    }

    @Test
    void testFindIncidencesById2() {
        ExamIncidenceRepository examIncidenceRepository = mock(ExamIncidenceRepository.class);
        when(examIncidenceRepository.findById((Long) any())).thenReturn(Optional.empty());
        ResponseEntity<ExamIncidence> actualFindIncidencesByIdResult = (new ExamIncidenceController(
                examIncidenceRepository)).findIncidencesById(123L);
        assertNull(actualFindIncidencesByIdResult.getBody());
        assertEquals("<404 NOT_FOUND Not Found,[]>", actualFindIncidencesByIdResult.toString());
        assertEquals(HttpStatus.NOT_FOUND, actualFindIncidencesByIdResult.getStatusCode());
        assertTrue(actualFindIncidencesByIdResult.getHeaders().isEmpty());
        verify(examIncidenceRepository).findById((Long) any());
    }
}

