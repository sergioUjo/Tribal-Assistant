package com.example.tribalassistent.data.model.gamedata;

import java.util.Map;

import lombok.Data;

@Data
public class Building {
    private String id;
    private String image;
    private String description;
    private Integer required_level;
    private Integer max_level;
    private Integer min_level;
    private Integer wood;
    private Float wood_factor;
    private Integer clay;
    private Float clay_factor;
    private Integer iron;
    private Float iron_factor;
    private Integer food;
    private Float food_factor;
    private Integer build_time;
    private Float build_time_factor;
    private Integer build_time_offset;
    private Integer hitpoints;
    private Float hitpoints_factor;
    private Integer function;
    private Integer function_factor;
    private Integer points;
    private Float points_factor;
    private String building_type;
    private Integer order;
    private Map<Integer, LevelCosts> individual_level_costs;

}
