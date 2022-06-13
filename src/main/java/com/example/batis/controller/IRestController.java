package com.example.batis.controller;

import com.example.batis.model.NewRegionDTO;
import com.example.batis.model.RegionDTO;
import com.example.batis.model.Regions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Region RestController", description = "RestController for list of regions")
public interface IRestController {

    @Operation(summary = "Get all regions")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    Regions findAll();

    @Operation(summary = "Get region by specific name and short name")
    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    RegionDTO findByNameAndShortname(@RequestParam("name") String name, @RequestParam("short") String shortName);

    @Operation(summary = "Get region by id")
    @GetMapping(value = "/")
    RegionDTO findById(@RequestParam long id);

    @Operation(summary = "Delete region from database by id")
    @DeleteMapping(value = "/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@RequestParam long id);

    @Operation(summary = "Add new region to the database")
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    void addRegion(@RequestBody NewRegionDTO newRegionDTO);

    @Operation(summary = "Update region by id")
    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    void updateRegion(@RequestBody RegionDTO region);
}
