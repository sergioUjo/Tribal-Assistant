package com.example.tribalassistent.data.model.building;

import com.example.tribalassistent.client.service.connection.RequestType;
import com.example.tribalassistent.data.model.Requestable;

import lombok.Data;

@Data
public class Complete implements Requestable<Void> {
    private Integer job_id;
    private String location;
    private Integer price;
    private Integer village_id;

    public Complete(Integer job_id, Integer village_id) {
        this.location = "slot";
        this.price = 0;
        this.job_id = job_id;
        this.village_id = village_id;
    }

    @Override
    public RequestType getType() {
        return RequestType.BUILDING_COMPLETE;
    }

    @Override
    public Class<Void> getResponse() {
        return Void.class;
    }
}
