package com.example.tribalassistent.data.model.building;

import com.example.tribalassistent.client.service.connection.RequestType;
import com.example.tribalassistent.data.model.Requestable;

import lombok.Data;

@Data
public class Upgrade implements Requestable<Upgrading> {
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

    @Override
    public RequestType getType() {
        return RequestType.BUILDING_UPGRADE;
    }

    @Override
    public Class<Upgrading> getResponse() {
        return Upgrading.class;
    }
}
