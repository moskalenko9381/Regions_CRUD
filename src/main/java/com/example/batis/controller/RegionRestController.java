package com.example.batis.controller;

import com.example.batis.exception.ExceptionMapper;
import com.example.batis.exception.regions.exceptions.RegionException;
import com.example.batis.model.Region;
import com.example.batis.model.RegionDTO;
import com.example.batis.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionRestController implements IRestController {
    @Autowired
    RegionService regionService;

    public ResponseEntity<List<RegionDTO>> findAll() {
        return ResponseEntity.ok(regionService.findAll());
    }

    public ResponseEntity<RegionDTO> findByNameAndShortname(@RequestParam Region region) {
        return ResponseEntity.ok(regionService.findByNameAndShortname(region));
    }

    public ResponseEntity<RegionDTO> findById(@RequestParam long id) {
        return ResponseEntity.ok(regionService.findById(id));
    }

    public ResponseEntity<String> deleteById(@RequestParam long id) {
        return ResponseEntity.ok(regionService.deleteById(id));
    }

    public ResponseEntity<RegionDTO> addRegion(@RequestBody Region region) {
        return ResponseEntity.ok(regionService.addRegion(region));
    }

    public ResponseEntity<RegionDTO> updateRegion(@RequestBody RegionDTO region) {
        return ResponseEntity.ok(regionService.updateRegion(region));
    }

    @ExceptionHandler
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        int exceptionCode = ExceptionMapper.map(exception).getStatusCodeValue();
        String message = "Exception message: " + exception.getMessage();
        if (exception instanceof RegionException)
            message += "\nException reason: " + ((RegionException) exception).getReason();
        return ResponseEntity.status(exceptionCode).body(message);
    }
}

