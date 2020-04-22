package com.example.tribalassistent.data.model.gamedata;

import com.example.tribalassistent.data.model.common.Resources;

import lombok.Data;

@Data
class BaseData {
    private Integer resource_production;
    private Integer storage_capacity;
    private Integer population_limit;
    private Integer hospital_healing_duration;
    private Integer resource_boost_duration;
    private Float resource_boost_value;
    private Integer instant_trade_ratio;
    private Integer cancel_command_duration;
    private Integer spy_speed;
    private Integer loot_protection_duration;
    private Float loot_protection_value;
    private Integer luck_min;
    private Integer luck_max;
    private Integer nobleman_effect_min;
    private Integer nobleman_effect_max;
    private Resources reset_order_resources;
    private Integer show_daily_bonus_after_login_nr;
    private Integer show_second_village_after_login_nr;
    private Float exp_to_level_exponent;
    private Integer exp_to_level_factor;
    private Float crown_to_exp_exponent;
    private Integer resource_to_exp_factor;
    private Integer knight_relocation_time;
}

