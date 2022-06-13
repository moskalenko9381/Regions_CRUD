package com.example.batis.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Entity of new region without id for POST")
public class NewRegionDTO {
    @Schema(description = "Full name of region", example = "Moscow")
    private String name;

    @Schema(description = "Short name of region", example = "Msc")
    private String shortName;
}
