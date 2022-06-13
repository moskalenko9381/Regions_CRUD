package com.example.batis;


import com.example.batis.exception.regions.exceptions.EmptyListOfRegionsException;
import com.example.batis.exception.regions.exceptions.RegionAlreadyExistsException;
import com.example.batis.exception.regions.exceptions.RegionDeletionException;
import com.example.batis.model.NewRegionDTO;
import com.example.batis.model.Region;
import com.example.batis.model.RegionDTO;
import com.example.batis.model.RegionDTOMapper;
import com.example.batis.repository.RegionMapper;
import com.example.batis.service.RegionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@MybatisTest
@Import(RegionService.class)
class RegionServiceTest {
    @Autowired
    RegionService regionService;

    @MockBean
    RegionMapper regionMapper;

    @Test
    void FindById_success() {
        Optional<Region> dto = Optional.of(new Region(1L, "Saint Petersburg" ,"SPb"));
        when(regionMapper.findById(1)).thenReturn(dto);
        RegionDTO region = regionService.findById(1);
        assertEquals("Saint Petersburg", region.getName());
        assertEquals("SPb", region.getShortName());
    }

    @Test
    void DeleteById_success() {
        when(regionMapper.deleteById(1)).thenReturn(true);
        regionService.deleteById(1);
        verify(regionMapper, times(1)).deleteById(1L);
    }

    @Test
    void DeleteById_error() {
        when(regionMapper.deleteById(500)).thenReturn(false);
        assertThrows(RegionDeletionException.class, () -> {
            regionService.deleteById(500);
        });
    }

    @Test
    void AddRegion_success() {
        NewRegionDTO newRegionDTO = new NewRegionDTO("Moscow", "Msc");
        when(regionMapper.addRegion(newRegionDTO.getName(), newRegionDTO.getShortName())).thenReturn(1);
        regionService.addRegion(newRegionDTO);
        verify(regionMapper, times(1))
                .addRegion(newRegionDTO.getName(), newRegionDTO.getShortName());
    }

    @Test
    void AddRegion_error() {
        NewRegionDTO newRegionDTO = new NewRegionDTO("Chita", "Ch");
        when(regionMapper.addRegion(newRegionDTO.getName(), newRegionDTO.getShortName()))
                .thenThrow(new DuplicateKeyException("Exists"));
        assertThrows(RegionAlreadyExistsException.class, () -> {
            regionService.addRegion(newRegionDTO);
        });
    }

    @Test
    void FindAll_success() {
        List<RegionDTO> testList = List.of(new RegionDTO(1L, "Chita", "Ch"));
        List<Region> regions = testList.stream().map(RegionDTOMapper::asRegion).collect(Collectors.toList());
        when(regionMapper.findAll()).thenReturn(regions);
        List<RegionDTO> regionsResponse = regionService.findAll();
        assertEquals(testList.size(), regionsResponse.size());
    }

    @Test
    void FindAll_error() {
        when(regionMapper.findAll()).thenReturn(new ArrayList<Region>());
        assertThrows(EmptyListOfRegionsException.class, () -> {
            regionService.findAll();
        });
    }
}
