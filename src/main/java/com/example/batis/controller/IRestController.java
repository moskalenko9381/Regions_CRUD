package com.example.batis.controller;

import com.example.batis.model.Region;
import com.example.batis.model.RegionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Region RestController", description = "RestController for list of regions")
public interface IRestController {

    @Operation(summary = "Get all regions")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<RegionDTO>> findAll();

    @Operation(summary = "Get region by specific name and short name")
    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RegionDTO> findByNameAndShortname(@RequestBody Region region);

    @Operation(summary = "Get region by id")
    @GetMapping(value = "/")
    ResponseEntity<RegionDTO> findById(@RequestParam long id);

    @Operation(summary = "Delete region from database by id")
    @DeleteMapping(value = "/")
    ResponseEntity<String> deleteById(@RequestParam long id);

    @Operation(summary = "Add new region to the database")
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<RegionDTO> addRegion(@RequestBody Region region);

    @Operation(summary = "Update region by id")
    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RegionDTO> updateRegion(@RequestBody RegionDTO region);
}
