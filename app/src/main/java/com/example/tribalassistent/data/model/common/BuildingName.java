package com.example.tribalassistent.data.model.common;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BuildingName {
    HEAD_QUARTER("headquarter"),
    TIMBER_CAMP("timber_camp"),
    CLAY_PIT("clay_pit"),
    IRON_MINE("iron_mine"),
    BARRACKS("barracks"),
    STATUE("statue"),
    HOSPITAL("hospital"),
    WALL("wall"),
    FARM("farm"),
    WAREHOUSE("warehouse"),
    CHAPEL("chapel"),
    RALLY_POINT("rally_point"),
    MARKET("market"),
    ACADEMY("academy"),
    PRECEPTORY("preceptory");

    private final String name;

    public String getName() {
        return name;
    }

    public static List<String> getNames() {
        List<String> list = new ArrayList<>();
        for (BuildingName building : BuildingName.values()) {
            list.add(building.getName());
        }
        return list;
    }

}
