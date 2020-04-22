package com.example.tribalassistent.data.model.building;

import lombok.Data;

@Data
public class Upgrade {
    private String building;
    private Integer village_id;
    private String location;
    private Boolean premium;

    public Upgrade(String building_name, int villageId) {
        this.building = building_name;
        village_id = villageId;
        location = "hq";
        premium = false;
    }
}
