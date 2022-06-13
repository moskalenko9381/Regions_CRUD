package com.example.batis.model;

import java.util.List;
import java.util.stream.Collectors;


public class RegionDTOMapper {

    public static RegionDTO asRegionDto(Region region) {
        return new RegionDTO(region.getId(), region.getName(), region.getShortName());
    }

    public static Region asRegion(RegionDTO region) {
        return new Region(region.getId(), region.getName(), region.getShortName());
    }

    public static Regions asRegions(List<Region> regionsList) {
        return regionsList.stream()
                .map(RegionDTOMapper::asRegionDto)
                .collect(Collectors.toCollection(Regions::new));
    }
}
