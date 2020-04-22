package com.example.tribalassistent.data.model.gamedata;

import lombok.Data;

@Data
class Unit {
    private String name;
    private String building;
    private Integer required_level;
    private Integer wood;
    private Integer clay;
    private Integer iron;
    private Integer food;
    private Integer build_time;
    private Integer attack;
    private Integer def_inf;
    private Integer def_kav;
    private Integer def_arc;
    private Integer points_att;
    private Integer points_def;
    private Integer speed;
    private Integer load;
    private Integer type;
    private Integer special;
}
