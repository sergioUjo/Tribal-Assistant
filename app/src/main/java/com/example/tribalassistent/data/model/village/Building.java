package com.example.tribalassistent.data.model.village;

import java.util.Map;

import lombok.Data;

@Data
public class Building {
    private Integer level;
    private Integer required_level;
    private Map<String, Research> researches;
}
