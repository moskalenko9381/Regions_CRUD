package com.example.batis.model;

import com.example.batis.repository.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegionDTOMapper {

    @Autowired
    RegionMapper regionMapper;

    @Bean
    public RegionDTOMapper newRegionDtoMapper() {
        return new RegionDTOMapper();
    }

    public static Region regionDtoToRegion(RegionDTO dto) {
        return new Region(dto.getName(), dto.getShortName());
    }

    public static RegionDTO regionToRegionDTO(Region region) {
        return new RegionDTO(1L, region.getName(), region.getShortName());
    }
}
