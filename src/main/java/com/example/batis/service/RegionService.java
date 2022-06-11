package com.example.batis.service;

import com.example.batis.exception.regions.exceptions.EmptyListOfRegionsException;
import com.example.batis.exception.regions.exceptions.NoSuchElementException;
import com.example.batis.exception.regions.exceptions.RegionAlreadyExistsException;
import com.example.batis.exception.regions.exceptions.RegionDeletionException;
import com.example.batis.model.Region;
import com.example.batis.model.RegionDTO;
import com.example.batis.repository.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RegionService {
    @Autowired
    private RegionMapper regionMapper;

    public List<RegionDTO> findAll() {
        List<RegionDTO> regions = regionMapper.findAll();
        if (regions.isEmpty())
            throw new EmptyListOfRegionsException();
        return regions;
    }

    public RegionDTO findById(long id) {
        Optional<RegionDTO> region = regionMapper.findById(id);
        return region.orElseThrow(() -> new NoSuchElementException(id));
    }

    public RegionDTO findByNameAndShortname(Region region) {
        Optional<RegionDTO> regionDto = regionMapper.findByNameAndShortname(region);
        return regionDto.orElseThrow(() -> new NoSuchElementException(region.getName(), region.getShortName()));
    }

    public String deleteById(long id) {
        if (!regionMapper.deleteById(id)) throw new RegionDeletionException();
        return "Region with id " + id + " was successfully deleted.";
    }


    public RegionDTO addRegion(Region region) {
        try {
            regionMapper.addRegion(region);
        } catch (DuplicateKeyException e) {
            throw new RegionAlreadyExistsException(region.getName(), region.getShortName());
        }
        return regionMapper.getLastRegion();
    }

    public RegionDTO updateRegion(RegionDTO region) {
        try {
            if (!regionMapper.updateRegion(region))
                throw new NoSuchElementException(region.getId());
        } catch (DuplicateKeyException e) {
            throw new RegionAlreadyExistsException(region.getName(), region.getShortName());
        }
        return findById(region.getId());
    }
}