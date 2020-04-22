package com.example.tribalassistent.data.model.gamedata;

import lombok.Data;

@Data
class Research {
    private String type;
    private String description;
    private Integer required_level;
    private String building;
    private Float function;
}
