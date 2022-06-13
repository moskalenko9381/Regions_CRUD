package com.example.batis.service;

import com.example.batis.exception.regions.exceptions.EmptyListOfRegionsException;
import com.example.batis.exception.regions.exceptions.NoSuchElementException;
import com.example.batis.exception.regions.exceptions.RegionAlreadyExistsException;
import com.example.batis.exception.regions.exceptions.RegionDeletionException;
import com.example.batis.model.*;
import com.example.batis.repository.RegionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RegionService {
    private final RegionMapper regionMapper;

    @Autowired
    public RegionService(RegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    public Regions findAll() {
        log.info("Find all regions: findAll");
        List<Region> regionsList = regionMapper.findAll();
        Regions regions = RegionDTOMapper.asRegions(regionsList);
        if (regions.isEmpty()) {
            throw new EmptyListOfRegionsException();
        }
        return regions;
    }

    @Cacheable("regions")
    public RegionDTO findById(long id) {
        log.info("Getting region by id: {}", id);
        Optional<Region> optRegion = regionMapper.findById(id);
        Region region = optRegion.orElseThrow(() -> new NoSuchElementException(id));
        return RegionDTOMapper.asRegionDto(region);
    }

    @Cacheable("regions")
    public RegionDTO findByNameAndShortname(String name, String shortName) {
        log.info("Getting region by name: {} and shortname: {}", name, shortName);
        Optional<Region> optRegion = regionMapper.findByNameAndShortname(name, shortName);
        Region region = optRegion.orElseThrow(() -> new NoSuchElementException(name, shortName));
        return RegionDTOMapper.asRegionDto(region);
    }

    @CacheEvict("regions")
    public void deleteById(long id) {
        log.info("Deleting region by id: {}", id);
        if (!regionMapper.deleteById(id)) {
            throw new RegionDeletionException();
        }
    }


    public void addRegion(NewRegionDTO newRegionDTO) {
        log.info("Add new region: addRegion");
        try {
            regionMapper.addRegion(newRegionDTO.getName(), newRegionDTO.getShortName());
        } catch (DuplicateKeyException e) {
            throw new RegionAlreadyExistsException(newRegionDTO.getName(), newRegionDTO.getShortName());
        }
    }

    public void updateRegion(RegionDTO region) {
        log.info("Update region by id: {}", region.getId());
        try {
            if (!regionMapper.updateRegion(RegionDTOMapper.asRegion(region))) {
                throw new NoSuchElementException(region.getId());
            }
        } catch (DuplicateKeyException e) {
            throw new RegionAlreadyExistsException(region.getName(), region.getShortName());
        }
    }
}
