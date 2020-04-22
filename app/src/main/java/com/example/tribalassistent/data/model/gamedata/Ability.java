package com.example.tribalassistent.data.model.gamedata;

import lombok.Data;

@Data
class Ability {
    private Integer percentage;
    private String type;
    private Integer bonus_percentage;
    //TODO condition not correctly mapped
    private Object conditions;
    private Float chance;
    private String speed;

}
