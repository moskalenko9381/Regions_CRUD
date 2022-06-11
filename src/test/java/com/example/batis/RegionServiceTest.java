package com.example.batis;


import com.example.batis.exception.regions.exceptions.EmptyListOfRegionsException;
import com.example.batis.exception.regions.exceptions.RegionAlreadyExistsException;
import com.example.batis.exception.regions.exceptions.RegionDeletionException;
import com.example.batis.model.Region;
import com.example.batis.model.RegionDTO;
import com.example.batis.model.RegionDTOMapper;
import com.example.batis.repository.RegionMapper;
import com.example.batis.service.RegionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@MybatisTest
@Import(RegionService.class)
public class RegionServiceTest {
    @Autowired
    RegionService regionService;

    @MockBean
    RegionMapper regionMapper;

    @Test
    void test_findById_success() {
        Optional<RegionDTO> dto = Optional.of(new RegionDTO(1L, "Saint Petersburg" ,"SPb"));
        Mockito.when(regionMapper.findById(1)).thenReturn(dto);
        RegionDTO region = regionService.findById(1);
        assertThat(region.getName().equals("Saint Petersburg"));
        assertThat(region.getShortName().equals("SPb"));
    }

    @Test
    void test_deleteById_success() {
        Mockito.when(regionMapper.deleteById(1)).thenReturn(true);
        String res = regionService.deleteById(1);
        assertThat(res.equals("Region with id 1 was successfully deleted."));
    }

    @Test
    void test_deleteById_error() {
        Mockito.when(regionMapper.deleteById(500)).thenReturn(false);
        assertThrows(RegionDeletionException.class, () -> {
            regionService.deleteById(500);
        });
    }

    @Test
    void test_addRegion_success() {
        Region region = new Region("Moscow", "Msc");
        Mockito.when(regionMapper.addRegion(region)).thenReturn(1);
        Mockito.when(regionMapper.getLastRegion()).thenReturn(RegionDTOMapper.regionToRegionDTO(region));
        assertEquals(region.getName(), regionService.addRegion(region).getName());
    }

    @Test
    void test_addRegion_error() {
        Region region = new Region("Chita", "Ch");
        Mockito.when(regionMapper.addRegion(region)).thenThrow(new DuplicateKeyException("Exists"));
        assertThrows(RegionAlreadyExistsException.class, () -> {
            regionService.addRegion(region);
        });
    }

    @Test
    void test_findAll_success() {
        List<RegionDTO> testList = List.of(new RegionDTO(1L, "Chita", "Ch"));
        Mockito.when(regionMapper.findAll()).thenReturn(List.of(new RegionDTO(1L, "Chita", "Ch")));
        List<RegionDTO> regions = regionService.findAll();
        assertEquals(testList.size(), regions.size());
    }

    @Test
    void test_findAll_error() {
        Mockito.when(regionMapper.findAll()).thenReturn(new ArrayList<>());
        assertThrows(EmptyListOfRegionsException.class, () -> {
            regionService.findAll();
        });
    }
}
