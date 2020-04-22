package com.example.tribalassistent.data.model.village;

import com.example.tribalassistent.data.model.building.Job;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Data;

@Data
public class VillageData {
    @JsonProperty("Village/village")
    private Village village;

    @JsonProperty("Village/unitInfo")
    private UnitInfo unitInfo;

    //TODO
    @JsonProperty("Timeline/events")
    private Object events;

    @JsonProperty("Command/ownCommands")
    private Object ownCommands;

    @JsonProperty("Command/foreignCommands")
    private Object foreignCommands;

    @JsonProperty("Scouting/info")
    private Object ScoutingInfo;

    @JsonProperty("Building/queue")
    private List<Job> queue;

    @JsonProperty("Transport/list")
    private Object transport;

    @JsonProperty("Hospital/patients")
    private Object patients;

    @JsonProperty("Academy/info")
    private Object academyInfo;
}
