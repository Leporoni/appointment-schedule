package com.leporonitech.appointmentschedule.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.leporonitech.appointmentschedule.model.Region;
import com.leporonitech.appointmentschedule.repositories.RegionRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RegionControllerTest {

    @Test
    void testFindAllRegions() {
        RegionRepository regionRepository = mock(RegionRepository.class);
        when(regionRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<?> actualFindAllRegionsResult = (new RegionController(regionRepository)).findAllRegions();
        assertEquals("<200 OK OK,[],[]>", actualFindAllRegionsResult.toString());
        assertTrue(actualFindAllRegionsResult.hasBody());
        assertEquals(HttpStatus.OK, actualFindAllRegionsResult.getStatusCode());
        assertTrue(actualFindAllRegionsResult.getHeaders().isEmpty());
        verify(regionRepository).findAll();
    }

    @Test
    void testFindAllRegions2() {
        Region region = new Region();
        region.setRegion("us-east-2");
        region.setTotal_exams(1000);
        region.setId(123L);

        ArrayList<Region> regionList = new ArrayList<>();
        regionList.add(region);
        RegionRepository regionRepository = mock(RegionRepository.class);
        when(regionRepository.findAll()).thenReturn(regionList);
        ResponseEntity<?> actualFindAllRegionsResult = (new RegionController(regionRepository)).findAllRegions();
        assertEquals("<200 OK OK,[Region(id=123, region=us-east-2, total_exams=1000)],[]>",
                actualFindAllRegionsResult.toString());
        assertTrue(actualFindAllRegionsResult.getHeaders().isEmpty());
        assertTrue(actualFindAllRegionsResult.hasBody());
        assertEquals(HttpStatus.OK, actualFindAllRegionsResult.getStatusCode());
        verify(regionRepository).findAll();
    }

    @Test
    void testFindRegionsById() {
        Region region = new Region();
        region.setRegion("us-east-2");
        region.setTotal_exams(1000);
        region.setId(123L);
        RegionRepository regionRepository = mock(RegionRepository.class);
        when(regionRepository.findById((Long) any())).thenReturn(Optional.of(region));
        ResponseEntity<Region> actualFindRegionsByIdResult = (new RegionController(regionRepository)).findRegionsById(123L);
        assertEquals("<200 OK OK,Region(id=123, region=us-east-2, total_exams=1000),[]>",
                actualFindRegionsByIdResult.toString());
        assertTrue(actualFindRegionsByIdResult.getHeaders().isEmpty());
        assertTrue(actualFindRegionsByIdResult.hasBody());
        assertEquals(HttpStatus.OK, actualFindRegionsByIdResult.getStatusCode());
        verify(regionRepository).findById((Long) any());
    }

    @Test
    void testFindRegionsById2() {
        RegionRepository regionRepository = mock(RegionRepository.class);
        when(regionRepository.findById((Long) any())).thenReturn(Optional.empty());
        ResponseEntity<Region> actualFindRegionsByIdResult = (new RegionController(regionRepository)).findRegionsById(123L);
        assertNull(actualFindRegionsByIdResult.getBody());
        assertEquals("<404 NOT_FOUND Not Found,[]>", actualFindRegionsByIdResult.toString());
        assertEquals(HttpStatus.NOT_FOUND, actualFindRegionsByIdResult.getStatusCode());
        assertTrue(actualFindRegionsByIdResult.getHeaders().isEmpty());
        verify(regionRepository).findById((Long) any());
    }
}

