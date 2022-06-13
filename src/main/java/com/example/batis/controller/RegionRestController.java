package com.example.batis.controller;

import com.example.batis.exception.ExceptionMapper;
import com.example.batis.model.ErrorResponse;
import com.example.batis.model.NewRegionDTO;
import com.example.batis.model.RegionDTO;
import com.example.batis.model.Regions;
import com.example.batis.service.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RegionRestController implements IRestController {
    private final RegionService regionService;

    @Autowired
    public RegionRestController(RegionService regionService) {
        this.regionService = regionService;
    }

    @Override
    public Regions findAll() {
        return regionService.findAll();
    }

    @Override
    public RegionDTO findByNameAndShortname(String name, String shortName) {
        return regionService.findByNameAndShortname(name, shortName);
    }

    @Override
    public RegionDTO findById(@RequestParam long id) {
        return regionService.findById(id);
    }

    @Override
    public void deleteById(@RequestParam long id) {
        regionService.deleteById(id);
    }

    @Override
    public void addRegion(@RequestBody NewRegionDTO newRegionDTO) {
        regionService.addRegion(newRegionDTO);
    }

    @Override
    public void updateRegion(@RequestBody RegionDTO newRegionDTO) {
        regionService.updateRegion(newRegionDTO);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception exception) {
        int exceptionCode = ExceptionMapper.map(exception).getStatusCodeValue();
        log.error("Exception intercepted: ", exception);
        return ResponseEntity.status(exceptionCode).body(new ErrorResponse(exception));
    }
}

