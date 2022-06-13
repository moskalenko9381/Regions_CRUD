package com.example.batis.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Region {
    private Long id;
    private String name;
    private String shortName;
}

