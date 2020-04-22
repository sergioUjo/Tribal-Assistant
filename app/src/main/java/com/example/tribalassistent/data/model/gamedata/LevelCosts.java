package com.example.tribalassistent.data.model.gamedata;

import com.example.tribalassistent.data.model.common.Resources;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
class LevelCosts extends Resources {
    private String build_time;
}
