package com.example.batis.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;

@Schema(description = "List of RegionDTO")
public class Regions extends ArrayList<RegionDTO> {
}