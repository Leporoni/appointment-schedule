package com.leporonitech.appointmentschedule.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.leporonitech.appointmentschedule.model.AgeGroup;
import com.leporonitech.appointmentschedule.repositories.AgeGroupRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AgeGroupControllerTest {

    @Test
    void testFindAllAgeGroup() {
        AgeGroupRepository ageGroupRepository = mock(AgeGroupRepository.class);
        when(ageGroupRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<?> actualFindAllAgeGroupResult = (new AgeGroupController(ageGroupRepository)).findAllAgeGroup();
        assertEquals("<200 OK OK,[],[]>", actualFindAllAgeGroupResult.toString());
        assertTrue(actualFindAllAgeGroupResult.hasBody());
        assertEquals(HttpStatus.OK, actualFindAllAgeGroupResult.getStatusCode());
        assertTrue(actualFindAllAgeGroupResult.getHeaders().isEmpty());
        verify(ageGroupRepository).findAll();
    }

    @Test
    void testFindByIdAgeGroup() {
        AgeGroup ageGroup = new AgeGroup();
        ageGroup.setGroup_i(1);
        ageGroup.setId(123L);
        ageGroup.setGroup_n(1);
        ageGroup.setDescription("The characteristics of someone or something");
        AgeGroupRepository ageGroupRepository = mock(AgeGroupRepository.class);
        when(ageGroupRepository.findById((Long) any())).thenReturn(Optional.of(ageGroup));
        ResponseEntity<AgeGroup> actualFindByIdAgeGroupResult = (new AgeGroupController(ageGroupRepository))
                .findByIdAgeGroup(123L);
        assertEquals("<200 OK OK,AgeGroup(id=123, group_i=1, group_n=1, description=The characteristics of someone or"
                + " something),[]>", actualFindByIdAgeGroupResult.toString());
        assertTrue(actualFindByIdAgeGroupResult.getHeaders().isEmpty());
        assertTrue(actualFindByIdAgeGroupResult.hasBody());
        assertEquals(HttpStatus.OK, actualFindByIdAgeGroupResult.getStatusCode());
        verify(ageGroupRepository).findById((Long) any());
    }

    @Test
    void testFindByIdAgeGroup2() {
        AgeGroupRepository ageGroupRepository = mock(AgeGroupRepository.class);
        when(ageGroupRepository.findById((Long) any())).thenReturn(Optional.empty());
        ResponseEntity<AgeGroup> actualFindByIdAgeGroupResult = (new AgeGroupController(ageGroupRepository))
                .findByIdAgeGroup(123L);
        assertNull(actualFindByIdAgeGroupResult.getBody());
        assertEquals("<404 NOT_FOUND Not Found,[]>", actualFindByIdAgeGroupResult.toString());
        assertEquals(HttpStatus.NOT_FOUND, actualFindByIdAgeGroupResult.getStatusCode());
        assertTrue(actualFindByIdAgeGroupResult.getHeaders().isEmpty());
        verify(ageGroupRepository).findById((Long) any());
    }

    @Test
    void testNewAgeGroup() {
        AgeGroup ageGroup = new AgeGroup();
        ageGroup.setGroup_i(1);
        ageGroup.setId(123L);
        ageGroup.setGroup_n(1);
        ageGroup.setDescription("The characteristics of someone or something");
        AgeGroupRepository ageGroupRepository = mock(AgeGroupRepository.class);
        when(ageGroupRepository.save((AgeGroup) any())).thenReturn(ageGroup);
        AgeGroupController ageGroupController = new AgeGroupController(ageGroupRepository);

        AgeGroup ageGroup1 = new AgeGroup();
        ageGroup1.setGroup_i(1);
        ageGroup1.setId(123L);
        ageGroup1.setGroup_n(1);
        ageGroup1.setDescription("The characteristics of someone or something");
        assertSame(ageGroup, ageGroupController.newAgeGroup(ageGroup1));
        verify(ageGroupRepository).save((AgeGroup) any());
    }

    @Test
    void testDeleteAgeGroup() {
        AgeGroupRepository ageGroupRepository = mock(AgeGroupRepository.class);
        doNothing().when(ageGroupRepository).deleteById((Long) any());
        (new AgeGroupController(ageGroupRepository)).deleteAgeGroup(123L);
        verify(ageGroupRepository).deleteById((Long) any());
    }
}

