package com.example.tribalassistent.data.model.village;

import lombok.Data;

@Data
public class LevelChange {
    private String building;
    private Integer change;
    private Integer level;
    private Integer village_id;
}
