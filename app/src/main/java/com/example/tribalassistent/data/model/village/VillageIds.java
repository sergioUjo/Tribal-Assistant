package com.example.tribalassistent.data.model.village;

import com.example.tribalassistent.client.service.connection.RequestType;
import com.example.tribalassistent.data.model.Requestable;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VillageIds implements Requestable<VillageGameBatch> {
    List<Integer> village_ids;

    @Override
    public RequestType getType() {
        return RequestType.GET_VILLAGE_DATA;
    }

    @Override
    public Class<VillageGameBatch> getResponse() {
        return VillageGameBatch.class;
    }
}
