package com.example.tribalassistent.data.model.gamedata;

import com.example.tribalassistent.data.model.common.Resources;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

import lombok.Data;

@Data
public class GameData {
    @JsonProperty("GameData/baseData")
    private BaseData baseData;

    @JsonProperty("GameData/costsPerCoin")
    private Resources costsPerCoin;

    @JsonProperty("GameData/research")
    private Map<String, Research> research;

    @JsonProperty("GameData/buildings")
    private Map<String, Building> buildings;

    @JsonProperty("GameData/units")
    private Map<String, Unit> units;

    @JsonProperty("GameData/officers")
    private Map<String, Officer> officers;

    @JsonProperty("GameData/premium")
    private Object premium;

    @JsonProperty("WorldConfig/config")
    private Object config;

    @JsonProperty("Achievement/all")
    private Object all;
}
